import org.junit.Test;

public class MysqlTest {

  @Test
  public void read(){
    Database db = new MySql();
    db.read();
  }

  @Test
  public void readWithReadonlyUserOk(){
    Database db = new DBAccessProxy(new MySql(), "web");
    db.read();
  }

  @Test(expected = RuntimeException.class)
  public void writeWithReadOnlyUserFails(){
    Database db = new DBAccessProxy(new MySql(), "web");
    db.write();
  }

  @Test
  public void writeWithWriteUserOk(){
    Database db = new DBAccessProxy(new MySql(), "root");
    db.write();
  }
}