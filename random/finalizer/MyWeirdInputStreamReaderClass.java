package random.finalizer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.Cleaner;

class MyWeirdInputStreamReaderClass {

  BufferedInputStream bis = null;

  void someReadInputMethod() throws FileNotFoundException {
    BufferedInputStream bis = new BufferedInputStream(new FileInputStream("pathToFile"));
    // ... logic to initialize bis variable but user
    // doesn't close the stream
  }

  // Don't use finalize in production code, bad idea!
  @Override
  protected void finalize() {
    try {
      bis.close();
    } catch (IOException io) {
      // ... log a message
//      Cleaner
    }
  }
}