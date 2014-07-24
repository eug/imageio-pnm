imageio-pnm
===========

A Java ImageIO plugin for the Portable Any Map image format. This project implements read/write support using ImageIO framework for all Portable Any Map formats (pgm, ppm, pbm and pnm).

# Usage

For sake of simplification, to register all plugins to ImageIO framework, just call the method ```PNMRegistry.registerAllServicesProviders()``` . For a better understanding in how to use this library, take a look at ```samples/PNMViewer.java```. A simple way to use is:

```java
PNMRegistry.registerAllServicesProviders();
final BufferedImage bi = ImageIO.read("image.pgm");
ImageIO.write(bi, "pgm", new File("image_2.pgm"));
```

# Build
Just ```mvn install```!

# License

MIT

