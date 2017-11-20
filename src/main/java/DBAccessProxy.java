import javax.naming.NoPermissionException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class DBAccessProxy implements Database {
  private Database db;
  private String user;
  private List<String> permissions;

  public DBAccessProxy(Database db, String user) {
    this.db = db;
    this.user = user;
  }

  private void readPermissions(){
    try {
      permissions = Files.readAllLines(Paths.get("src/main/resources/permissions.txt"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private boolean hasPermission(Permission p){
    if(permissions == null){
      readPermissions();
    }
    return permissions.stream()
            .map(l -> l.split(" "))
            .filter(l -> l[0].equals(user))
            .map(l -> Arrays.asList(l[1].toLowerCase().split(",")))
            .anyMatch(permissions -> permissions.contains(p.toString().toLowerCase()));
  }

  @Override
  public Object read(){
    if(hasPermission(Permission.READ)){
      return db.read();
    }
    throw new RuntimeException(new NoPermissionException());
  }

  @Override
  public Object write() {
    if(hasPermission(Permission.WRITE)){
      return db.read();
    }
    throw new RuntimeException(new NoPermissionException());
  }

  public enum Permission {
    READ,
    WRITE
  }
}
