## Java Lazy Access Proxy

1. Erstelle einen Proxy für die Klasse MySql.
1. Implementiere den Proxy so, dass alle Tests grün sind.

Methoden die du evtl brauchst:

```java
  private void readPermissions(){
    try {
      permissions = Files.readAllLines(Paths.get("src/main/resources/permissions.txt"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private boolean hasPermission(Permission p){
    return permissions.stream()
            .map(l -> l.split(" "))
            .filter(l -> l[0].equals(user))
            .map(l -> Arrays.asList(l[1].toLowerCase().split(",")))
            .anyMatch(permissions -> permissions.contains(p.toString().toLowerCase()));
  }
```
