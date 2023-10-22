# API

This is the API for Essentials-S, a Essentials plugin for Sponge API 8 and beyond.

## Backwards compatibility policy

When it comes to backwards compatibility its something that is great for external plugins but can hinder future development. 
For this reason backwards compatibility is part of the API yet it does not last forever. 

The policy is that any and all methods and field must not be removed for two recommended build. 
This will give time for developers to update plugins. 
While the process is that methods and fields can be removed its not likely that methods and fields will be removed.

## Maven

```xml
        <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

        <dependencies>
            <dependency>
	        <groupId>com.github.Essentialss</groupId>
	        <artifactId>API</artifactId>
	        <version>0.0.7</version>
	    </dependency>
        </dependencies>


```
