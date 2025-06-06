package forth;

import forth.commands.Command;
import forth.exceptions.CommandNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Supplier;

public class CommandFactory {
  private static final Map<String, Supplier<Command>> commands = new HashMap<>();

  private CommandFactory() {}
  static {
    try (InputStream is = CommandFactory.class.getClassLoader().getResourceAsStream("commands.properties")) {
      if (is == null) {
        throw new IllegalStateException("No command properties file");
      }
      Properties properties = new Properties();
      properties.load(is);

      for (String key : properties.stringPropertyNames()) {
        String fqcn = properties.getProperty(key).trim();
        try {
          Class<?> cls = Class.forName(fqcn);
          if (!Command.class.isAssignableFrom(cls)) {
            throw new IllegalStateException("Invalid command class " + fqcn);
          }
          @SuppressWarnings("unchecked")
          Class<? extends Command> commandClass = (Class<? extends Command>) cls;
          Constructor<? extends Command> ctor = commandClass.getDeclaredConstructor();
          commands.put(key, () -> {
            try {
              return ctor.newInstance();
            } catch (Exception e) {
              throw new RuntimeException("Failed to instantiate command " + fqcn, e);
            }
          });
        } catch (ReflectiveOperationException e) {
          throw new IllegalStateException("Can't register command by name " + key , e);
        }
      }
    } catch (IOException e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  public static Command create(String name) {
    Supplier<Command> supp = commands.get(name);
    if (supp == null) {
      throw new CommandNotFoundException("Command " + name + " not found");
    }
    return supp.get();
  }
}
