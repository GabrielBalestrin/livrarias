package livrarias.DAO;

import java.math.BigDecimal;
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
import livrarias.classes.autores;

public class autoresDAO {

    public void cadastrar(autores e) {

        Connection conn = bacoMysql.conectabanco();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
         
            stmt = conn.prepareStatement("INSERT INTO autores (nome,endereco,numero,bairro,cidade,cpf) values (?,?,?,?,?,?)");
                    
                    
                   
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getEndereco());
            stmt.setInt(3, e.getNumero());
            stmt.setString(4, e.getBairro());
            stmt.setString(5, e.getCidade());
            stmt.setBigDecimal(6, new BigDecimal(e.getCPF()));

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Autor cadastrado com sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(autoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

 
    }
    public List<autores> listar() {

        Connection conn = bacoMysql.conectabanco();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<autores> Autores = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM autores");
            rs = stmt.executeQuery();
            while (rs.next()) {
              autores a = new autores();
                a.setId(rs.getInt("id_autor"));
                a.setNome(rs.getString("nome"));

                Autores.add(a);

            }
        } catch (SQLException ex) {
            Logger.getLogger(editorasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Autores;

}
}