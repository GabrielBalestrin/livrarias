package livrarias.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import livrarias.bacoMysql;
import livrarias.classes.Editoras;

import livrarias.classes.Livros;
import livrarias.classes.autores;

public class livrosDAO {

    public void cadastrar(Livros l) {
        Connection conn = bacoMysql.conectabanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO livros (id_editora,id_autor,titulo,ano) VALUES (?,?,?,?)");
            stmt.setInt(1, l.getEditora().getId());
            stmt.setInt(2, l.getAutor().getId());
            stmt.setString(3, l.getTitulo());
            stmt.setInt(4, l.getAno());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");

        } catch (SQLException ex) {

        }

    }

    public void alterar(Livros l) {
        Connection conn = bacoMysql.conectabanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        
        try {
            stmt = conn.prepareStatement("UPDATE livros set id_editora = ?, id_autor = ?, titulo = ?, ano = ?  where id_livro = ? ");
             stmt.setInt(1, l.getEditora().getId());
            stmt.setInt(2, l.getAutor().getId());
            stmt.setString(3, l.getTitulo());
            stmt.setInt(4, l.getAno());
            stmt.setInt(5, l.getId_livro());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Livro alterado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(livrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
           

       
        
    } 

    public List<Livros> listar() {

        Connection conn = bacoMysql.conectabanco();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<Livros> livros = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("select livros.id_livro,editoras.nome as nome_editora,autores.nome as nome_autores,livros.titulo,livros.ano from livros\n"
                    + "inner join editoras on (editoras.id_editora = livros.id_editora)\n"
                    + "inner  join autores on (autores.id_autor = livros.id_autor)");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Livros l = new Livros();
                l.setId_livro(rs.getInt("id_livro"));
                l.setTitulo(rs.getString("titulo"));
                l.setAno(rs.getInt("ano"));

                Editoras e = new Editoras();
                e.setNome(rs.getString("nome_editora"));
                l.setEditora(e);

                autores a = new autores();
                a.setNome(rs.getString("nome_autores"));
                l.setAutor(a);

                livros.add(l);

            }
        } catch (SQLException ex) {
            Logger.getLogger(editorasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livros;

    }

    public List<Livros> pesquisar(String texto) {

        Connection conn = bacoMysql.conectabanco();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<Livros> livros = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("select livros.id_livro,editoras.nome as nome_editora,autores.nome as nome_autores,livros.titulo,livros.ano from livros\n"
                    + "inner join editoras on (editoras.id_editora = livros.id_editora)\n"
                    + "inner  join autores on (autores.id_autor = livros.id_autor) where titulo like ? ");
            stmt.setString(1, "%" + texto + "%");

            rs = stmt.executeQuery();
            while (rs.next()) {
                Livros l = new Livros();
                l.setId_livro(rs.getInt("id_livro"));
                l.setTitulo(rs.getString("titulo"));
                l.setAno(rs.getInt("ano"));

                Editoras e = new Editoras();
                e.setNome(rs.getString("nome_editora"));
                l.setEditora(e);

                autores a = new autores();
                a.setNome(rs.getString("nome_autores"));
                l.setAutor(a);

                livros.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(livrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livros;
    }


      public void excluir(Livros l) {
        Connection conn = bacoMysql.conectabanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("DELETE FROM livros WHERE  id_livro = ? ");
            stmt.setInt(1, l.getId_livro());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "livro excluído com sucesso!");

        } catch (SQLException ex) {

        }
    }

}
