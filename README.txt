A Java based QR code checker for automated event system management

After building with maven:

- copy the SNAPSHOT jar into bin folder
- run the following -> java -jar gatePalocs-1.0-SNAPSHOT.jar

Tried many times to fix this by creating properly classpath in MANIFEST file
but got import error for many libraries!!! The problem is that the main jar can not reference directly libraries to which it depends
so they must be added e compacted manually for the time being; not a very automated deployment(just in the case of deploy on raspberry)...

In case of general purpose machine deployment, i have updated properly the maven file, as it can be seen from the last commit

*The client supports object detection thanks to openCV; this was possible thanks to raspberry pi usage, can not be done on other kind of embedded systems*
*typically in IoT systems(having large bandwidth) image is sent across the network and processed by a backend(normally today is performed in the cloud)*
*I can consider this development outdated or with this specific use case, i.e. having a general purpose host or a powerful embdedded machine(as raspberry)*
