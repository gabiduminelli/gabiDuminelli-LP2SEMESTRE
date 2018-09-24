package GUIs;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;
import java.io.File;
import javax.swing.text.JTextComponent;
import Auxiliar.*;
import DAOs.*;
import Entidades.*;
import DAOs.DAOTurma;
import DAOs.DAOAluno;
import Entidades.Turma;
import Entidades.Aluno;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Point;
import myUtil.MinhaJOptionPane1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import myUtil.JanelaPesquisar;
import Auxiliar.ManipulaArquivo;
import java.util.List;
import java.util.ArrayList;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GUITurmaHasAluno extends JFrame {

    public static void main(String[] args) {
        new GUITurmaHasAluno();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("Id Turma: ");
    private JLabel lbIdTurma = new JLabel("Id Turma");
    private JTextField fdIdTurma = new JTextField(10);

    private JLabel lbRaAluno = new JLabel("Ra Aluno");
    private JTextField fdRaAluno = new JTextField(45);

    private JPanel painelNortes = new JPanel(new GridLayout(2, 1));
    private JPanel painelNorteCima = new JPanel();
    private JPanel painelNorteBaixo = new JPanel();
    private JPanel painelCentralFora = new JPanel(new BorderLayout());
    private JPanel painelCentral = new JPanel();
    private JPanel painelSul = new JPanel();
    private JLabel labelBranco = new JLabel();

    JButton btInserir = new JButton(new ImageIcon(getClass().getResource("/icones/add.png")));
    JButton btSalvar = new JButton(new ImageIcon(getClass().getResource("/icones/confirmar.png")));
    JButton btRemover = new JButton(new ImageIcon(getClass().getResource("/icones/deletar.png")));
    JButton btAtualizar = new JButton(new ImageIcon(getClass().getResource("/icones/att.png")));
    JButton btBuscar = new JButton(new ImageIcon(getClass().getResource("/icones/search.png")));
    JButton btCancelar = new JButton(new ImageIcon(getClass().getResource("/icones/cancelar.png")));
    JButton btListar = new JButton(new ImageIcon(getClass().getResource("/icones/listar.png")));
    JButton btAluno = new JButton(new ImageIcon(getClass().getResource("/icones/if_30_171488.png")));

    DAOTurma controle = new DAOTurma();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    Turma Turma = new Turma();
    DAOTurma daoTurma = new DAOTurma();
    Turma turma = new Turma();
    DAOAluno daoAluno = new DAOAluno();
    Aluno aluno = new Aluno();

    public GUITurmaHasAluno() {
        setSize(725, 340);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Aluno dos turmaes");

        painelCentral.setLayout(new GridLayout(1, 1));
        painelCentral.add(lbRaAluno);
        painelCentral.add(fdRaAluno);

        fdRaAluno.setEnabled(false);

        List<String> combo = new ArrayList<>();

        cp.setBackground(Color.white);

        cp.add(painelNortes, BorderLayout.NORTH);
        cp.add(painelCentralFora, BorderLayout.WEST);
        cp.add(painelSul, BorderLayout.SOUTH);

        painelCentralFora.add(labelBranco, BorderLayout.NORTH);
        painelCentralFora.add(painelCentral, BorderLayout.SOUTH);
        painelNortes.add(painelNorteCima);
        painelNortes.add(painelNorteBaixo);
        painelNorteCima.add(labelTitulo);
        painelNorteCima.add(fdIdTurma);
        painelNorteBaixo.add(btBuscar);
        painelNorteBaixo.add(btInserir);
        painelNorteBaixo.add(btAtualizar);
        painelNorteBaixo.add(btRemover);
        painelNorteBaixo.add(btSalvar);
        painelNorteBaixo.add(btCancelar);
        painelNorteBaixo.add(btListar);
        painelNorteBaixo.add(btAluno);
        painelNorteCima.setBackground(Color.white);
        painelNorteBaixo.setBackground(Color.white);
        painelCentralFora.setBackground(Color.white);
        painelCentral.setBackground(Color.white);
        painelSul.setBackground(Color.white);
        btInserir.setBackground(Color.WHITE);
        btSalvar.setBackground(Color.WHITE);
        btRemover.setBackground(Color.WHITE);
        btAtualizar.setBackground(Color.WHITE);
        //fdIdTurma.setBackground(Color.green);

        btBuscar.setBackground(Color.WHITE);
        btCancelar.setBackground(Color.WHITE);
        btListar.setBackground(Color.WHITE);
        btAluno.setBackground(Color.WHITE);

        labelTitulo.setFont(new Font("Courier New", Font.BOLD, 20));
        fdIdTurma.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbIdTurma.setFont(new Font("Courier New", Font.BOLD, 17));
        lbRaAluno.setFont(new Font("Courier New", Font.BOLD, 17));
        fdIdTurma.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdRaAluno.setFont(new Font("Courier New", Font.PLAIN, 17));
        labelAviso.setFont(new Font("Courier New", Font.BOLD, 20));
        btInserir.setVisible(false);
        btAtualizar.setVisible(false);
        btRemover.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        btAluno.setVisible(false);

        painelSul.add(labelAviso);

        fdIdTurma.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                List<Turma> listaTurma = daoTurma.listInOrderIdTurma();
                List<String> listaAuxiliar = new ArrayList<>();
                for (Turma t : listaTurma) {
                    for (Aluno aluno : t.getAlunoList()) {
                        listaAuxiliar.add("" + t.getIdTurma() + ", " + t.getCursoCodigoCurso().getNomeCurso() + " - "
                                + aluno.getRaAluno() + ", " + aluno.getPessoaCpf().getNomePessoa());
                    }
                }

                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x + getWidth() + 5, getBounds().y).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split(" - ");
                        fdIdTurma.setText(aux[0].split(",")[0]);

                        fdRaAluno.setText(aux[1].split(",")[0]);

                    }
                }
            }
        });

        fdRaAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoAluno.listInOrderNomeStrings("idAluno");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5, getBounds().y).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        fdRaAluno.setText(aux[0]);
                    } else {
                        fdRaAluno.requestFocus();
                        fdRaAluno.selectAll();
                    }
                }
            }
        });
        fdIdTurma.requestFocus();
        fdIdTurma.selectAll();
        //fdIdTurma.setBackground(Color.GREEN);
        //labelAviso.setText("Digite um ID e clic [Pesquisar]");
        btAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                List<Aluno> listaAluno = daoAluno.listInOrderRaAluno();
                List<String> listaAuxiliar = new ArrayList<>();
                for (Aluno a: listaAluno) {
                    listaAuxiliar.add("" + a.getRaAluno()+ ", " + a.getPessoaCpf().getNomePessoa());
                }
            
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2
                            + getWidth() + 5,
                            fdRaAluno.getBounds().y + fdRaAluno.getHeight()).getValorRetornado();
                    fdRaAluno.setText(selectedItem+"");
                    
                }

        
    }
    }
            
    );
    btBuscar.addActionListener ( 
        new ActionListener() {
            @Override
        public void actionPerformed
        (ActionEvent e
        
            ) {
                try {
                turma = new Turma();
                int idTurma = Integer.valueOf(fdIdTurma.getText());
                turma.setIdTurma(Integer.valueOf(fdIdTurma.getText()));
                turma = controle.obter(turma.getIdTurma());
                //labelAviso.setBackground(Color.green);
                if (turma != null) {

                    btAtualizar.setVisible(true);
                    btRemover.setVisible(true);
                    btInserir.setVisible(true);
                    btListar.setVisible(false);
                    labelAviso.setText("Encontrado na lista!");
                    btAluno.setVisible(true);
                    //labelAviso.setBackground(Color.green);
                } else {

                    labelAviso.setText("Não encontrado!");
                    labelAviso.setBackground(Color.red);
                    btInserir.setVisible(true);
                    btAtualizar.setVisible(false);
                    btRemover.setVisible(false);
                    btListar.setVisible(false);
                    btAluno.setVisible(false);
                }
            } catch (Exception erro) {
                labelAviso.setText("Preencha os campos corretamente!");
                labelAviso.setBackground(Color.red);

            }
        }

    }

    );

    btInserir.addActionListener (
             
        new ActionListener() {
            @Override
        public void actionPerformed
        (ActionEvent ae
        
            ) {

                List<Aluno> lista = turma.getAlunoList();
            String aux[] = fdRaAluno.getText().split(",");

            Aluno aluno1 = daoAluno.obter(Integer.valueOf(aux[0].trim()));

            System.out.println(aluno.getRaAluno());
            System.out.println("aluno vazia");

            lista.add(aluno1);

            turma.setAlunoList(lista);

            daoTurma.atualizar(turma);

            btInserir.setEnabled(true);
            btRemover.setEnabled(true);
            labelAviso.setText("Relacionamento criado");
        }
    }

    );

    btRemover.addActionListener (
             
        new ActionListener() {
            @Override
        public void actionPerformed
        (ActionEvent ae
        
            ) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                    "Confirma a exclusão do aluno " + aluno.getRaAluno() + " de " + turma.getIdTurma() + "?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {

                List<Aluno> lista = turma.getAlunoList();
                lista.remove(aluno);
                turma.setAlunoList(lista);
                daoTurma.atualizar(turma);

                fdIdTurma.setText("");
                fdRaAluno.setText("");

                btInserir.setEnabled(true);
                btRemover.setEnabled(false);
                labelAviso.setText("Relacionamento excluído");
            }
        }
    }

    );
    btAtualizar.addActionListener (
             
        new ActionListener() {
            @Override
        public void actionPerformed
        (ActionEvent e
        
            ) {
                acao = false;
            lbRaAluno.setEnabled(true);
            fdRaAluno.setEnabled(true);
            btSalvar.setVisible(true);
            btCancelar.setVisible(true);
            btBuscar.setVisible(false);
            btRemover.setVisible(false);
            btAtualizar.setVisible(false);
            btListar.setVisible(false);
            btAluno.setVisible(true);
        }
    }

    );
    btListar.addActionListener (
             
        new ActionListener() {
            @Override
        public void actionPerformed
        (ActionEvent e
        
            ) {
                new ProfessorHasTitulacaoGUIListagem(controle.listInOrderNomeStrings("raTurma"), daoAluno.listInOrderNomeStrings("idAluno"), cp);
        }
    }

    );

//        btnList.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                //ContratoGUIListagem listagem = new ContratoGUIListagem(daocontrato.list(), getBounds().x, getBounds().y);
//            }
//        });
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco

    fdIdTurma.addFocusListener (
             
        new FocusListener() { //ao receber o foco, fica verde
            @Override
        public void focusGained
        (FocusEvent fe
        
        
        ) {

            }

            @Override
        public void focusLost
        (FocusEvent fe
        
        
    

    ) { //ao perder o foco, fica branco

            }
        });

    fdRaAluno.addFocusListener (
             
        new FocusListener() { //ao receber o foco, fica verde
            @Override
        public void focusGained
        (FocusEvent fe
        
        
        ) {

            }

            @Override
        public void focusLost
        (FocusEvent fe
        
        
    

    ) { //ao perder o foco, fica branco

            }
        });

//    btBuscar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//        try {
//            int IdTurma = Integer.valueOf(fdIdTurma.getText());
//            int RaAluno = Integer.valueOf(fdRaAluno.getText());
//
//            turma = daoTurma.obter(IdTurma);
//            aluno = daoAluno.obter(RaAluno);
//
//            boolean f = false;
//            System.out.println(turma.getAlunoList().size());
//            for (Aluno a : turma.getAlunoList()) {
//                if (a.getRaAluno() == RaAluno) {
//                    f = true;
//                }
//            }
//
//            if (f) {
//                labelAviso.setText("Relacionamento encontrado");
//                btInserir.setEnabled(false);
//                btRemover.setEnabled(true);
//            } else {
//                labelAviso.setText("Relacionamento inexistente");
//                btInserir.setEnabled(true);
//                btRemover.setEnabled(false);
//            }
//            //labelAviso.setText("");
//
//        } catch (Exception ex) {
//            //ex.printStackTrace();
//        }
//            
//            
//      
//            }
//        });
    addWindowListener(
             
        new WindowAdapter() {
            @Override
        public void windowClosing
        (WindowEvent e
        
            ) {
                dispose();
        }
    }

    );
    setLocationRelativeTo(
            

    null);
    setVisible(
            

true);
    }
}
