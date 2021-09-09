package janela;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Arquivo {


	private BufferedWriter bw;

	public boolean criarArquivo(String nome) throws IOException{
		File arquivo = new File(nome + ".txt");
		if (existeFile(nome)){
			arquivo.delete();
		}
		arquivo.createNewFile();
		this.escreverFile(arquivo, "Tempo;DeltaTnext;Consumo;Nivel");
		return existeFile(nome);
	}

	public boolean existeFile(String nome){
		File arquivo = new File(nome + ".txt");
		return arquivo.exists();
	}

	public void escreverFile(File arq, String txt) throws IOException{
		FileWriter fw = new FileWriter(arq, true);
		bw = new BufferedWriter(fw);
		bw.write(txt);
		bw.newLine();
		bw.close();
		fw.close();
	}

	public void escreverRet(String retorno) throws IOException{
		String[] command = retorno.split(";");
		if (command.length > 2){
			if (!this.existeFile(command[0])){
				this.criarArquivo(command[0]);
			}
			File arq = this.retArquivo(command[0]);
			String txt = command[1] + ";" + command[2] + ";" + command[3] + ";" + command[4];
			this.escreverFile(arq, txt);
		}
	}

	public File retArquivo(String nome){
		File arquivo = new File(nome + ".txt");
		return arquivo;
	}

}
