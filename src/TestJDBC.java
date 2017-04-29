import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Created on 11/09/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Claudio
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestJDBC
{
	public static void main(String[] args)
	{
		try
		{
			System.out.println("Cargando el Driver JDBC");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Creando una nueva conexión JDBC con la DB");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/productosdb?user=root&password=lococaz");
			CallableStatement callableStatement = conexion.prepareCall("{CALL productosdb.SP_ListadoPorCostos(?)}");
			callableStatement.setDouble(1,200);
			ResultSet resultSet = callableStatement.executeQuery();
			
			while(resultSet.next())
			{
				System.out.println(resultSet.getString(2));
			}

			conexion.close();
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("El Driver JDBC de HSQL no fue encontrado");
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			System.out.println("Error");
			e.printStackTrace();
		}
		System.exit(0);
	}
}