package forth;

import forth.commands.Command;
import forth.exceptions.CommandNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Supplier;

public class CommandFactory {
  private static final Logger log = LoggerFactory.getLogger(CommandFactory.class);

  private static final Map<String, Supplier<Command>> commands = new HashMap<>();

  private CommandFactory() {}
  static {
    log.info("Initializing CommandFactory: loading commands from commands.properties");
    try (InputStream is = CommandFactory.class.getClassLoader().getResourceAsStream("commands.properties")) {
      if (is == null) {
        log.error("commands.properties not found in classpath");
        throw new IllegalStateException("No command properties file");
      }
      Properties properties = new Properties();
      properties.load(is);
      log.debug("Loaded {} entries from commands.properties", properties.size());

      for (String key : properties.stringPropertyNames()) {
        String fqcn = properties.getProperty(key).trim();
        log.debug("Registering command '{}' -> class '{}'", key, fqcn);
        try {
          Class<?> cls = Class.forName(fqcn);
          if (!Command.class.isAssignableFrom(cls)) {
            log.error("Class '{}' does not implement Command", fqcn);
            throw new IllegalStateException("Invalid command class " + fqcn);
          }
          @SuppressWarnings("unchecked")
          Class<? extends Command> commandClass = (Class<? extends Command>) cls;
          Constructor<? extends Command> ctor = commandClass.getDeclaredConstructor();
          commands.put(key, () -> {
            try {
              return ctor.newInstance();
            } catch (Exception e) {
              log.error("Failed to instantiate command '{}'", fqcn, e);
              throw new RuntimeException("Failed to instantiate command " + fqcn, e);
            }
          });
        } catch (ReflectiveOperationException e) {
          log.error("Failed to register command '{}' with class '{}'", key, fqcn, e);
          throw new IllegalStateException("Can't register command by name " + key , e);
        }
      }
      log.info("CommandFactory initialized successfully with {} commands", commands.size());
    } catch (IOException e) {
      log.error("I/O error loading commands.properties", e);
      throw new ExceptionInInitializerError(e);
    }
  }

  public static Command create(String name) {
    log.debug("Creating command instance for name '{}'", name);
    Supplier<Command> supp = commands.get(name);
    if (supp == null) {
      log.error("Command '{}' not found in registry", name);
      throw new CommandNotFoundException("Command " + name + " not found");
    }
    return supp.get();
  }
}
