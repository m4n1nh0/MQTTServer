package janela;

import java.io.IOException;
import java.io.OutputStream;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class ServidorConsoleStream extends OutputStream  {

	private TextArea textArea ;
	private int x = 0;

	public ServidorConsoleStream(TextArea textArea) {
		this.textArea = textArea;
	}

    public void write(int v) throws IOException {
    	Platform.runLater( () -> {
			if(v=='\n') {
				if(x++ > 100) {
    				x=0;
    				textArea.clear();
    			}
			}
			textArea.appendText(String.valueOf((char) v));
			}
    	);
    }

}
