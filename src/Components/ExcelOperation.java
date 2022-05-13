package Components;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExcelOperation {
    
    String urlFile;

    public ExcelOperation(String urlFile) {
        this.urlFile = urlFile;
    }

    public void write(String s) {
        try (DataOutputStream os = new DataOutputStream(new FileOutputStream(this.urlFile, false))) {
            os.writeUTF(s);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelOperation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String read() {
        String result = "";
        try (DataInputStream os = new DataInputStream(new FileInputStream(this.urlFile))) {
            result += os.readUTF();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelOperation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }

}
