package org.example.perruqueria.perruqueriadreams.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgendaDAO {

    public String mostrarAgenda(String fecha, String hora, Integer idTrabajador) {
        String sqlMostrarAgenda = "SELECT texto FROM agenda WHERE dia = '" + fecha + "' AND hora = '" + hora + "' AND id_trabajador = " + idTrabajador;
        String texto = "";
        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement sqlMostrarAgendaStmt = conn.prepareStatement(sqlMostrarAgenda);
            ResultSet rs = sqlMostrarAgendaStmt.executeQuery()) {
            
            while (rs.next()) {
                texto = rs.getString("texto");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return texto;
    }

    public boolean encontrarDia(String dia, String hora, Integer idTrabajador) {
        String sqlEncontrarDia = "SELECT COUNT(*) FROM agenda WHERE dia = ? AND hora = ? AND id_trabajador = ?";
        boolean encontrado = false;
    
        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement sqlEncontrarDiaStmt = conn.prepareStatement(sqlEncontrarDia)) {
            
            sqlEncontrarDiaStmt.setString(1, dia);
            sqlEncontrarDiaStmt.setString(2, hora);
            sqlEncontrarDiaStmt.setInt(3, idTrabajador);
            
            try (ResultSet rs = sqlEncontrarDiaStmt.executeQuery()) {
                if (rs.next()) {
                    encontrado = rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                throw new RuntimeException("BaseDatos NO encontrada");
            } else {
                ex.printStackTrace();
            }
        }
        return encontrado;
    }

    public boolean agregarAgenda(Agenda agenda) {
        String sqlAgregar = "INSERT INTO agenda (dia, hora, texto, id_trabajador) VALUES (?, ?, ?, ?)";
        boolean resultado = false;

        try (Connection conn = ConexionBaseDatos.getConexion();
        PreparedStatement psAgregarAgenda = conn.prepareStatement(sqlAgregar)) {
            psAgregarAgenda.setString(1, agenda.getDia());
            psAgregarAgenda.setString(2, agenda.getHora());
            psAgregarAgenda.setString(3, agenda.getTexto());
            psAgregarAgenda.setInt(4, agenda.getIdTrabajador());

            int filasAfectadas = psAgregarAgenda.executeUpdate();
            resultado = filasAfectadas > 0; // La insercion es correcta

            resultado = true; // La insercion es correcta
        } catch (SQLException ex) {
            if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                throw new RuntimeException("BaseDatos NO encontrada");
            } else {
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    public boolean actualizarAgenda(String dia, String hora, Integer idTrabajador, String texto) {
        String sqlActualizarAgenda = "UPDATE agenda SET texto = ? WHERE dia = ? AND hora = ? AND id_trabajador = ?";
        boolean resultado =  false;

        try (Connection conn = ConexionBaseDatos.getConexion();
            PreparedStatement pstmt = conn.prepareStatement(sqlActualizarAgenda)) {
            
            pstmt.setString(1, texto);
            pstmt.setString(2, dia);
            pstmt.setString(3, hora);
            pstmt.setInt(4, idTrabajador);
            
            int filasActualizadas = pstmt.executeUpdate();
            resultado = filasActualizadas > 0;

        } catch (SQLException ex) {
            if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                throw new RuntimeException("BaseDatos NO encontrada");
            } else {
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    public boolean eliminarAgenda(String dia, String hora, Integer idTrabajador) {
        String sqlEliminarAgenda = "DELETE FROM agenda WHERE dia = ? AND hora = ? AND id_trabajador = ?";
        boolean resultado = false;

        try (Connection conn = ConexionBaseDatos.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sqlEliminarAgenda)) {

            pstmt.setString(1, dia);
            pstmt.setString(2, hora);
            pstmt.setInt(3, idTrabajador);
            resultado = pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                throw new RuntimeException("BaseDatos NO encontrada");
            } else {
                ex.printStackTrace();
                resultado = false;
            }
        }
        return resultado;
    }
}