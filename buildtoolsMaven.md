# Introduction #

If you already use Maven, then making use of modeldata project is simply a matter of adding the buildtools plugin to your pom.


# Details #

  1. Add the modeldata dependency to your project
    1. Add the dependency repository:
```
<repositories>
    <repository>
        <id>unitt</id>
        <name>UnitT Repository</name>
        <url>http://unitt.googlecode.com/svn/repository</url>
    </repository>
</repositories>
```
    1. Add the dependency:
```
<dependencies>
    <dependency>
        <groupId>com.unitt</groupId>
        <artifactId>modeldata</artifactId>
        <version>1.0.0</version>
        <type>jar</type>
    </dependency>
</dependencies>
```
  1. Add the plugin to your project
    1. Add the plugin repository:
```
<pluginRepositories>
    <pluginRepository>
        <id>unitt</id>
        <name>UnitT Repository</name>
        <url>http://unitt.googlecode.com/svn/repository/</url>
    </pluginRepository>
</pluginRepositories>
```
    1. Add the plugin:
```
<build>
    <plugins>
        <plugin>
            <groupId>com.unitt</groupId>
            <artifactId>buildtools</artifactId>
            <extensions>true</extensions>
            <configuration>
                <attach>true</attach>
            </configuration>
            <executions>
                <execution>
                    <phase>generate-sources</phase>
                    <goals>
                        <goal>modeldata</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```