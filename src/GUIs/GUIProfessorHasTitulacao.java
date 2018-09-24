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
import DAOs.DAOProfessor;
import DAOs.DAOTitulacao;
import Entidades.Professor;
import Entidades.Titulacao;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GUIProfessorHasTitulacao extends JFrame {

    public static void main(String[] args) {
        new GUIProfessorHasTitulacao();
    }
    private Container cp;
    private JLabel labelAviso = new JLabel("Avisos");
    private JLabel labelTitulo = new JLabel("Ra Professor: ");
    private JLabel lbRaProfessor = new JLabel("Ra Professor");
    private JTextField fdRaProfessor = new JTextField(10);

    private JLabel lbIdTitulacao = new JLabel("Id Titulacao");
    private JTextField fdIdTitulacao = new JTextField(45);

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
    JButton btTit = new JButton(new ImageIcon(getClass().getResource("/icones/if_30_171488.png")));

    DAOProfessor controle = new DAOProfessor();
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    Boolean acao;
    Font fonte = new Font("Courier New", Font.BOLD, 20);
    Font fonteL = new Font("Courier New", Font.PLAIN, 14);

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    Professor Professor = new Professor();
    DAOProfessor daoProfessor = new DAOProfessor();
    Professor professor = new Professor();
    DAOTitulacao daoTitulacao = new DAOTitulacao();
    Titulacao titulacao = new Titulacao();

    public GUIProfessorHasTitulacao() {
        setSize(725, 340);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("Titulacao dos professores");

        painelCentral.setLayout(new GridLayout(1, 1));
        painelCentral.add(lbIdTitulacao);
        painelCentral.add(fdIdTitulacao);

        fdIdTitulacao.setEnabled(false);

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
        painelNorteCima.add(fdRaProfessor);
        painelNorteBaixo.add(btBuscar);
        painelNorteBaixo.add(btInserir);
        painelNorteBaixo.add(btAtualizar);
        painelNorteBaixo.add(btRemover);
        painelNorteBaixo.add(btSalvar);
        painelNorteBaixo.add(btCancelar);
        painelNorteBaixo.add(btListar);
        painelNorteBaixo.add(btTit);
        painelNorteCima.setBackground(Color.white);
        painelNorteBaixo.setBackground(Color.white);
        painelCentralFora.setBackground(Color.white);
        painelCentral.setBackground(Color.white);
        painelSul.setBackground(Color.white);
        btInserir.setBackground(Color.WHITE);
        btSalvar.setBackground(Color.WHITE);
        btRemover.setBackground(Color.WHITE);
        btAtualizar.setBackground(Color.WHITE);
        btBuscar.setBackground(Color.WHITE);
        btCancelar.setBackground(Color.WHITE);
        btListar.setBackground(Color.WHITE);
        btTit.setBackground(Color.WHITE);

        labelTitulo.setFont(new Font("Courier New", Font.BOLD, 20));
        fdRaProfessor.setFont(new Font("Courier New", Font.PLAIN, 20));
        lbRaProfessor.setFont(new Font("Courier New", Font.BOLD, 17));
        lbIdTitulacao.setFont(new Font("Courier New", Font.BOLD, 17));
        fdRaProfessor.setFont(new Font("Courier New", Font.PLAIN, 17));
        fdIdTitulacao.setFont(new Font("Courier New", Font.PLAIN, 17));
        labelAviso.setFont(new Font("Courier New", Font.BOLD, 20));
        btInserir.setVisible(false);
        btAtualizar.setVisible(false);
        btRemover.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        btTit.setVisible(false);

        painelSul.add(labelAviso);

        fdRaProfessor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                List<Professor> listaProfessor = daoProfessor.listInOrderRaProfessor();
                List<String> listaAuxiliar = new ArrayList<>();
                for (Professor p : listaProfessor) {
                    for (Titulacao titulacao : p.getTitulacaoList()) {
                        listaAuxiliar.add("" + p.getRaProfessor() + ", " + p.getPessoaCpf().getNomePessoa() + " - "
                                + titulacao.getIdTitulacao() + ", " + titulacao.getNomeTitulacao());
                    }
                }

                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x + getWidth() + 5, getBounds().y).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split(" - ");
                        fdRaProfessor.setText(aux[0].split(",")[0]);

                        fdIdTitulacao.setText(aux[1].split(",")[0]);

                    }
                }
            }
        });

        fdIdTitulacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoTitulacao.listInOrderNomeStrings("idTitulacao");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5, getBounds().y).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        fdIdTitulacao.setText(aux[0]);
                    } else {
                        fdIdTitulacao.requestFocus();
                        fdIdTitulacao.selectAll();
                    }
                }
            }
        });
        fdRaProfessor.requestFocus();
        fdRaProfessor.selectAll();
        //fdRaProfessor.setBackground(Color.GREEN);
        //labelAviso.setText("Digite um ID e clic [Pesquisar]");
        btTit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                List<String> listaAuxiliar = daoTitulacao.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2
                            + getWidth() + 5,
                            fdIdTitulacao.getBounds().y + fdIdTitulacao.getHeight()).getValorRetornado();
                    fdIdTitulacao.setText(selectedItem+"");
                    
                }

            }
        });
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    professor = new Professor();
                    int idProfessor = Integer.valueOf(fdRaProfessor.getText());
                    professor.setRaProfessor(Integer.valueOf(fdRaProfessor.getText()));
                    professor = controle.obter(professor.getRaProfessor());
                    //labelAviso.setBackground(Color.green);
                    if (professor != null) {

                        btAtualizar.setVisible(true);
                        btRemover.setVisible(true);
                        btInserir.setVisible(true);
                        btListar.setVisible(false);
                        labelAviso.setText("Encontrado na lista!");
                        btTit.setVisible(true);
                        //labelAviso.setBackground(Color.green);
                    } else {

                        labelAviso.setText("Não encontrado!");
                        labelAviso.setBackground(Color.red);
                        btInserir.setVisible(true);
                        btAtualizar.setVisible(false);
                        btRemover.setVisible(false);
                        btListar.setVisible(false);
                        btTit.setVisible(false);
                    }
                } catch (Exception erro) {
                    labelAviso.setText("Preencha os campos corretamente!");
                    labelAviso.setBackground(Color.red);

                }
            }

        }
        );

        btInserir.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {

                List<Titulacao> lista = professor.getTitulacaoList();
                String aux[]=fdIdTitulacao.getText().split(";");
                        
                Titulacao titulacao1 = daoTitulacao.obter(Integer.valueOf(aux[0].trim()));

                System.out.println(titulacao.getIdTitulacao());
                System.out.println("titulacao vazia");

                lista.add(titulacao1);

                professor.setTitulacaoList(lista);

                daoProfessor.atualizar(professor);

                btInserir.setEnabled(true);
                btRemover.setEnabled(true);
                labelAviso.setText("Relacionamento criado");
            }
        }
        );

        btRemover.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do titulacao " + titulacao.getNomeTitulacao() + " de " + professor.getRaProfessor() + "?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {

                    List<Titulacao> lista = professor.getTitulacaoList();
                    lista.remove(titulacao);
                    professor.setTitulacaoList(lista);
                    daoProfessor.atualizar(professor);

                    fdRaProfessor.setText("");
                    fdIdTitulacao.setText("");

                    btInserir.setEnabled(true);
                    btRemover.setEnabled(false);
                    labelAviso.setText("Relacionamento excluído");
                }
            }
        }
        );
        btAtualizar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                acao = false;
                lbIdTitulacao.setEnabled(true);
                fdIdTitulacao.setEnabled(true);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btRemover.setVisible(false);
                btAtualizar.setVisible(false);
                btListar.setVisible(false);
                btTit.setVisible(true);
            }
        }
        );
        btListar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                
                new ProfessorHasTitulacaoGUIListagem(daoProfessor.listInOrderNomeStrings("raProfessor"),daoTitulacao.listInOrderNomeStrings("nomeTitulacao"),cp);
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

        fdRaProfessor.addFocusListener(
                new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe
            ) {

            }

            @Override
            public void focusLost(FocusEvent fe
            ) { //ao perder o foco, fica branco

            }
        });

        fdIdTitulacao.addFocusListener(
                new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe
            ) {

            }

            @Override
            public void focusLost(FocusEvent fe
            ) { //ao perder o foco, fica branco

            }
        });

//    btBuscar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//        try {
//            int IdProfessor = Integer.valueOf(fdRaProfessor.getText());
//            int IdTitulacao = Integer.valueOf(fdIdTitulacao.getText());
//
//            professor = daoProfessor.obter(IdProfessor);
//            titulacao = daoTitulacao.obter(IdTitulacao);
//
//            boolean f = false;
//            System.out.println(professor.getTitulacaoList().size());
//            for (Titulacao a : professor.getTitulacaoList()) {
//                if (a.getIdTitulacao() == IdTitulacao) {
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
            public void windowClosing(WindowEvent e
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
