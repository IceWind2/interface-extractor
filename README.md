# Interface extractor
CLI application for extracting interface from java class source code.
## Requirements
* Java 8+
* Maven

## Installation
Clone this repository
```sh
git clone https://github.com/IceWind2/interface-extractor.git
```
Build with maven
```sh
mvn clean package
```

## Usage
Use application to create a file with interface. Pass the class file as argument.
```sh
java -jar interface-extractor-1.0-jar-with-dependencies.jar *PathToFile*
```

## Config
To configure settings, create file `config.yaml` in the same directory from which you run the application. Config fields are:
* className - target class
* whiteList - list of methods which must be included in the interface
* blackList - list of methods which must not be included in the interface
* accessModifiers - defines visibility scopes from which methods can be taken
* interfaceName - defines the name of resulting interface
* exportPath - defines where resulting file will be created

Config example:
```
className: TestClass
whiteList: [someMethod]
blackList: [get]
interfaceName: NewInterface
accessModifiers: [public, private]
exportPath: "./intfc.java"
```