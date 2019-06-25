package application;

import java.security.spec.KeySpec;
import java.util.Base64.*;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainController {

		@FXML
		private TextField txtMessageDepart;
	
		@FXML
		private TextField txtMessageFinal;
		
	    private static final String UNICODE_FORMAT = "UTF8";
	    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	    private KeySpec ks;
	    private SecretKeyFactory skf;
	    private Cipher cipher;
	    byte[] arrayBytes;
	    private String myEncryptionKey;
	    private String myEncryptionScheme;
	    SecretKey key;

	    public MainController () throws Exception {
	        myEncryptionKey = "2048 97:45:3b:f2:9a:a3:0a:6c:97:bf:13:8c:49:39:11:9c";
	        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
	        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
	        ks = new DESedeKeySpec(arrayBytes);
	        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
	        cipher = Cipher.getInstance(myEncryptionScheme);
	        key = skf.generateSecret(ks);
	    }
	    
	    @FXML
	    public void encrypt(ActionEvent event) {

	    	String encryptedString = null;
	        try {
	            cipher.init(Cipher.ENCRYPT_MODE, key);
	            byte[] plainText = txtMessageDepart.getText().getBytes(UNICODE_FORMAT);
	            byte[] encryptedText = cipher.doFinal(plainText);
	           encryptedString = new String(Base64.encodeBase64(encryptedText));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        txtMessageFinal.setText(encryptedString);
	    }

}
