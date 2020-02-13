/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aduitavilaran;

import com.opencsv.exceptions.CsvException;
import controller.ExecutionTime;
import controller.Validator;
import controller.readcsv.ReadCustomCsv;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author P05144
 */
public class AduitAvilaRan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Options options = new Options();
        CommandLine cmd = null;
        Boolean showHelpMessage = false;
        Boolean showVersion = false;
        String _input = null; //TABLA A AUDITAR
        String _audit = null; //COLUMMNA A AUDITAR
        String _output = null; //SALIDA
        double _baseline = 0; //BASELINE
        boolean _columns =false;
        ExecutionTime executionTime = new ExecutionTime(); //Execution Time
        try {

            options.addOption("h", "help", false, "show help");
            options.addOption("v", "version", false, "display version");
            options.addOption("c", "column", false, "display csv columns");
            options.addOption(Option.builder("i")
                    .longOpt("i")
                    .desc("FILE COMAND .CSV TO AUDIT")
                    .hasArg()
                    .argName("input").build());
            options.addOption(Option.builder("o")
                    .longOpt("o")
                    .desc("DIRECTORY OUTPUT AUDIT")
                    .hasArg()
                    .argName("output").build());
            options.addOption(Option.builder("a")
                    .longOpt("a")
                    .desc("PARAMATER TO AUDIT")
                    .hasArg()
                    .argName("audit").build());
            options.addOption(Option.builder("b")
                    .longOpt("b")
                    .desc("BASELINE AUDIT")
                    .hasArg()
                    .argName("baseline").build());

            //Parse command line arguments
            CommandLineParser parser = new DefaultParser();
            cmd = parser.parse(options, args);
            if (cmd.hasOption("h")) {
                showHelpMessage = true;
            }

            if (cmd.hasOption("v")) {
                showVersion = true;
            }
            if (cmd.hasOption("c")) {
                _columns = true;
            }
            if (cmd.hasOption("i")) {

                _input = cmd.getOptionValue("i").toUpperCase();

            }
            if (cmd.hasOption("o")) {

                _output = cmd.getOptionValue("o").toUpperCase();

            }
            if (cmd.hasOption("a")) {

                _audit = cmd.getOptionValue("a").toUpperCase();

            }
            
            if (cmd.hasOption("b")) {

                _baseline = Double.parseDouble(cmd.getOptionValue("b"));

            }
        } catch (ParseException ex) {
            //System.out.println("ParseException ERROR GENERAL "+ex.getMessage().toString());

            System.err.println("ParseException ERROR GENERAL " + ex.getMessage());
            System.exit(1);
        } catch (NumberFormatException ex) {

            System.err.println("ERROR: BASELINE DEBE SER NUMERO REAL " + ex.getMessage());
            System.exit(1);
        }

        if (showVersion == true) {
            System.out.println("1.0.0 \n auditavilaran Copyright (c)  Telefonica VENEZUELA ");
            //System.out.println("1.0.0");
            //System.out.println("Copyright (c)"+executionTime.getDate()+" Telefonica VENEZUELA");
            System.exit(0);
        }

        //show help
        if (showHelpMessage == true
                || _input == null
                || _audit == null
                || _output == null) {
            HelpFormatter formatter = new HelpFormatter();
            String header = "AVILA RAN AUDIT\n\n";
            String footer = "\n";
            footer += "Examples: \n";
            footer += "java -jar auditavilaran.jar "
                    + "-i FILE_COMAND_TO_AUDIT -o OUTPUT_DIRECTORY_AUDIT"
                    + "-a PARAMETER_TO_AUDIT -b BASELINE"
                    + "\n";
            formatter.printHelp("java -jar auditavilaran.jar -h", header, options, footer);
            System.exit(0);
        }
        if (!Validator.isFile(_input)){
            System.out.println(" NO SE ENCUENTRA EL ARCHIVO "+_input);
            System.exit(1);
        }
        if (!Validator.isDirectory(_output)){
            System.out.println(_output+" NO ES UN DIRECTORIO ");
            System.exit(1);
        }
        
        if (_columns == true) {
            try {
                ReadCustomCsv.getColumnsCSV(_input).forEach(System.out::println);
            } catch (IOException ex) {
                System.out.println("" + ex.getMessage());
            } catch (CsvException ex) {
                System.out.println("" + ex.getMessage());
            } finally {
                System.exit(0);
            }

        }
        
        System.out.println(" "+_input+ " "+_audit+ " "+_output);
        
        try {
            ReadCustomCsv.getDataAudit(_input, _audit).forEach((t) -> {
                System.out.println(" "+t.toString());
            });
        } catch (IOException ex) {
            System.out.println(""+ex.getMessage());
        } catch (CsvException ex) {
             System.out.println(""+ex.getMessage());
        }
        System.out.println(executionTime.getExecutionTime());
    }

}
