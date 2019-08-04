import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import java.io.IOException;
import java.io.OutputStream;
 
    public class ControlePorta {
    private OutputStream serialOut;
    private int taxa;
    private String portaCOM;
 
  
    public ControlePorta(String portaCOM, int taxa) {
    this.portaCOM = portaCOM;
    this.taxa = taxa;
    this.inicializar();
  }     
 
  //Médoto que verifica se a comunicação com a porta serial está ok
  private void inicializar() {
        try {
          //Define uma variável portId do tipo CommPortIdentifier para realizar a comunicação serial
          CommPortIdentifier portId = null;
          try {
            //Tenta verificar se a porta COM informada existe
            portId = CommPortIdentifier.getPortIdentifier(this.portaCOM);
          }catch (NoSuchPortException npe) {
            //Caso a porta COM não exista será exibido um erro 
            System.out.println("Porta COM não encontrada, verificar o Try do inicializar(ControlePorta)");
          }
          //Abre a porta COM 
          SerialPort port = (SerialPort) portId.open("Comunicação serial", this.taxa);
          serialOut = port.getOutputStream();
          port.setSerialPortParams(this.taxa, //taxa de transferência da porta serial 
                                   SerialPort.DATABITS_8, //taxa de 10 bits 8 (envio)
                                   SerialPort.STOPBITS_1, //taxa de 10 bits 1 (recebimento)
                                   SerialPort.PARITY_NONE); //receber e enviar dados
        }catch (Exception e) {
          e.printStackTrace();
        }//fechando o TryCatch
    }//fechando o inicializar
 
 
  //Método que fecha a comunicação com a porta serial
  public void close() {
    try {
        serialOut.close();
    }catch (IOException e) {
      System.out.println("Não foi possível fechar porta COM, veriricar o Try do close(ControlePorta)");
    }
  }//fechando o metodo Close()
 
  //Método que envia dados para a porta serial
  public void enviaDados(int opcao){
    try {
      serialOut.write(opcao);//escreve o valor na porta serial para ser enviado
    } catch (IOException ex) {
        System.out.println("Não foi possível enviar o dado, Virificar o Try do enviarDados(ControlePorta)");
    }//fechando TryCatch
  }//fechando enviarDados
}//fechando class Principal

