package ejemplojasper;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.PaisesDataSource;
import javax.swing.WindowConstants;
import model.ConnectionPool;
import model.ConnectionPoolSqlServer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author JorgeLPR
 */
public class EjemploJasper {

    public EjemploJasper(){

        try{

            JasperReport report;
            JasperPrint jprint=null;

            int options = Integer.parseInt(JOptionPane.showInputDialog("GENERAR INFORMES\n"
                    + "1- Array DataSource\n"
                    + "2- MySQL DataSource\n"
                    + "3- SQL Server DataSource\n"
                    + "4- Salir"));
            
            
            switch(options){
            
                case 1:
                    report = (JasperReport) JRLoader.loadObject(getClass().getResource("/reports/practica_paises_array_datasource.jasper"));
                    jprint = JasperFillManager.fillReport(report, null, PaisesDataSource.getDataSource());                                                
                break;
            
                case 2:
                    report = (JasperReport) JRLoader.loadObject(getClass().getResource("/reports/practica_paises_mysql_datasource.jasper"));
                    jprint = JasperFillManager.fillReport(report, null, ConnectionPool.getInstance().getConnection());                                                                    
                break;

                case 3:
                    report = (JasperReport) JRLoader.loadObject(getClass().getResource("/reports/practica_paises_sql_server_datasource.jasper"));
                    jprint = JasperFillManager.fillReport(report, null, ConnectionPoolSqlServer.getInstance().getConnection());                                                                    
                break;
                
                default:
                    System.out.println("Finalizando proceso...");
                break;
                
            }
            
            if(jprint!=null){            
                JasperViewer view = new JasperViewer(jprint, false);
                view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                view.setVisible(true);                        
            }
            
        }catch(HeadlessException | NumberFormatException | JRException ex){
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(EjemploJasper.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        EjemploJasper jasper = new EjemploJasper();
        

    }
    
}
