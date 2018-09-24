/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import DAOs.DAOProfessor;
import DAOs.DAOTitulacao;
import Entidades.Professor;
import Entidades.Titulacao;
import java.util.List;

/**
 *
 * @author Gabi
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Professor professor = new Professor();
        Titulacao titulacao = new Titulacao();
        DAOTitulacao daoTitulacao = new DAOTitulacao();
        DAOProfessor daoProfessor = new DAOProfessor();
        professor = daoProfessor.obter(2);
        
        System.out.println(professor.toString());
        List<Titulacao> listaTitulacao = professor.getTitulacaoList();
        //listaTitulacao.add(daoTitulacao.obter(1));
        //listaTitulacao.add(daoTitulacao.obter(2));
        professor.setTitulacaoList(listaTitulacao);
        //daoProfessor.atualizar(professor);
        
        listaTitulacao = professor.getTitulacaoList();
        for (int i = 0; i < listaTitulacao.size(); i++) {
            System.out.println(listaTitulacao.get(i)); 
        }
        
        
    }
    
}
