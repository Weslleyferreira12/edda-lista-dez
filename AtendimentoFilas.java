import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class AtendimentoFilas {
    private Queue<Cliente> filaClientes;
    private Funcionario[] funcionarios;
    private Random random;
    
    public AtendimentoFilas(int numFuncionarios) {
        filaClientes = new LinkedList<>();
        funcionarios = new Funcionario[numFuncionarios];
        random = new Random();
        
        for (int i = 0; i < numFuncionarios; i++) {
            funcionarios[i] = new Funcionario(i);
        }
    }
    
    public void adicionarCliente(Cliente cliente) {
        filaClientes.add(cliente);
        System.out.println("Cliente " + cliente.getId() + " entrou na fila.");
    }
    
    public void atenderClientes() {
        while (!filaClientes.isEmpty()) {
            Cliente cliente = filaClientes.poll(); 
            Funcionario funcionario = funcionarios[random.nextInt(funcionarios.length)]; 
            
            int tempoAtendimento = random.nextInt(10) + 1; 
            System.out.println("Cliente " + cliente.getId() + " sendo atendido pelo Funcionário " + funcionario.getId() + ". Tempo de atendimento: " + tempoAtendimento);
            
            cliente.aumentarTempoEspera(tempoAtendimento); 
            
            
            try {
                Thread.sleep(tempoAtendimento * 1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            System.out.println("Cliente " + cliente.getId() + " foi atendido. Tempo de espera total: " + cliente.getTempoEspera());
        }
    }
    
    public static void main(String[] args) {
        AtendimentoFilas sistema = new AtendimentoFilas(3); 

        sistema.adicionarCliente(new Cliente(1));
        sistema.adicionarCliente(new Cliente(2));
        sistema.adicionarCliente(new Cliente(3));
        sistema.adicionarCliente(new Cliente(4));
        sistema.adicionarCliente(new Cliente(5));
        sistema.adicionarCliente(new Cliente(6));
        sistema.adicionarCliente(new Cliente(7));
       
        sistema.atenderClientes();
    }
}
