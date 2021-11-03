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

public class editorasDAO {

    public void cadastrar(Editoras e) {
        Connection conn = bacoMysql.conectabanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO editoras (nome) values (?)");
            stmt.setString(1, e.getNome());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Editora cadastrada com sucesso!");

        } catch (SQLException ex) {

        }

    }

    public List<Editoras> listar() {

        Connection conn = bacoMysql.conectabanco();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<Editoras> editoras = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM editoras");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Editoras e = new Editoras();
                e.setId(rs.getInt("id_editora"));
                e.setNome(rs.getString("nome"));

                editoras.add(e);

            }
        } catch (SQLException ex) {
            Logger.getLogger(editorasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return editoras;

    }

    public void alterar(Editoras e) {
        Connection conn = bacoMysql.conectabanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("UPDATE editoras set nome = ? where id_editora = ? ");
            stmt.setString(1, e.getNome());
            stmt.setInt(2, e.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Editora alterada com sucesso!");

        } catch (SQLException ex) {

        }
    }

    public void excluir(Editoras e) {
        Connection conn = bacoMysql.conectabanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("DELETE FROM editoras WHERE  id_editora = ? ");
            stmt.setInt(1, e.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Editora excluída com sucesso!");

        } catch (SQLException ex) {

        }
    }

    public List<Editoras> pesquisar(String texto) {

        Connection conn = bacoMysql.conectabanco();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<Editoras> editoras = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM editoras where nome like ? ");
            stmt.setString(1, "%" + texto + "%");

            rs = stmt.executeQuery();
            while (rs.next()) {
                Editoras e = new Editoras();
                e.setId(rs.getInt("id_editora"));
                e.setNome(rs.getString("nome"));

                editoras.add(e);

            }
        } catch (SQLException ex) {
            Logger.getLogger(editorasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return editoras;
    }
}
