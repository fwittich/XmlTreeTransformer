# XmlTreeTransformer
This is a small but complete demo project for transforming data from xml into a special tree/json structure.
The basic idea is create a configuration in a database that allows the transformation of *any* XML into the povided structure.  

The use case for this (especially the target tree structure) comes from a personal background so it is quite possible this is of no use to anyone else. But you can imagine it as an adapter that takes an XML as output from one application and transforms it into a JSON structure as input for another application (hence the specific target tree structure).

## Trying out the project
For easy demonstration the project contains a simple and already configured example. 
To test it yourself you can use the provided swagger ui. 
* http://localhost:8080/app/swagger-ui.html
  * RootTreeElementId: 1
  * XML: [Example XML](https://github.com/fwittich/XmlTreeTransformer/blob/master/xml-tree-transformer/src/dev/resources/ExampleApplication.xml) 

You can look at the database configuration using the h2 console:
* http://localhost:8080/app/h2-console 
  * jdbc: jdbc:h2:mem:testdb  
  * user: sa (no password)

For further explanation look below.

## Target [TreeStructure](xml-tree-transformer/src/main/java/de/wittich/transformation/tree/TreeElement.java)
The elements of the tree consist of three fields:
* an id identifying an element
* a list of attributes (each consisting of an id and a value)
* a list of child elements


## How does the application work
The application takes any XML and extracts single values using XPath and transforms them by different, configurable means into other values for the target structure.
For this purpose for each value that should appear in the final tree structure a database entry exists that provides the following information
* an `XPath` to get the value from the XML
* a `TreePath` describing the place in the target [TreeStructure](#target-treestructure)
* an `AttributeId` to generate the [attribute](xml-tree-transformer/src/main/java/de/wittich/transformation/tree/TreeElementAttribute.java) for the tree element
* a name of a bean implementing a certain [interface](xml-tree-transformer/src/main/java/de/wittich/transformation/service/transformer/XMLValueTransformer.java) responsible for the transformation of the value extracted using the `XPath`
