import javax.swing.JTextArea;


import java.util.Vector; // added
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.html.HTML;

import java.awt.TextArea;

import javax.swing.JFrame;
import java.awt.EventQueue;
import java.awt.Font;

import edu.casetools.icase.mreasoner.actuators.device.Actuator;
import edu.casetools.icase.mreasoner.configs.data.MConfigs;
import edu.casetools.icase.mreasoner.database.core.implementations.PostgreSQL_DatabaseOperations;
import edu.casetools.icase.mreasoner.gui.Main;
import edu.casetools.icase.mreasoner.gui.controller.Controller;
import edu.casetools.icase.mreasoner.gui.model.Model;
import edu.casetools.icase.mreasoner.gui.view.View;
import edu.casetools.icase.argumentation.MySQL_ArgumentationDBOperations;
import edu.casetools.icase.argumentation.RulesCompiler;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;

import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import net.proteanit.sql.DbUtils;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Color;

import edu.casetools.icase.mreasoner.gui.Main;

public class HybridMain {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private ResultSet conflictsRS;
	MySQL_ArgumentationDBOperations conn = new MySQL_ArgumentationDBOperations();
//	PostgreSQL_DatabaseOperations connPG = new PostgreSQL_DatabaseOperations();
	//MySQL_ArgumentationDBOperations conn1;  
	public static String mainFile;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					HybridMain window = new HybridMain();
					window.frame.setVisible(true);
					//window.frame.setAlwaysOnTop(true); //always ontop
					window.frame.setResizable(true); //resizeable
					//initComponents();

					Toolkit tk = Toolkit.getDefaultToolkit();
					int xsize = (int) tk.getScreenSize().getWidth();
					int ysize = (int) tk.getScreenSize().getHeight();
					window.frame.setSize(xsize, ysize);
//					conn1 = new MySQL_ArgumentationDBOperations();
//					conn1.connect();
//					conn1.erasePossibleConflicts();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}
//		Vector<Actuator> actuators = new Vector<Actuator>(); //actuator
//		MConfigs configs = new MConfigs();
//		Model model = new Model(configs.getDBConfigs(), actuators);
//		View view = new View(configs);
//		Controller controller = new Controller(view,model,configs.getFilesConfigs());

	/**
	 * Create the application.
	 */
	public HybridMain() {
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize () {

		frame = new JFrame();
		frame.setTitle("Hybrid - Main");
		frame.getContentPane().setForeground(Color.WHITE);
		//frame.getContentPane().setLayout(new BorderLayout());
		//frame.getContentPane().add(ContentPane, BorderLayout.CENTER);
		//frame.setBounds(100, 100, 543, 355);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenu mnExit = new JMenu("Exit");
		mnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.exit(0);
				//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		menuBar.add(mnExit);

		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(372, 56, 613, 89);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		//		JButton btnLoadPotentialConflict = new JButton("Load Potential Conflict");
		//		btnLoadPotentialConflict.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent arg0) {
		//					conn = new MySQL_ArgumentationDBOperations();
		//					conn.connect();
		//					ResultSet rs = conn.getPossibleConflicts();
		//					table.setModel(DbUtils.resultSetToTableModel(rs));
		//		
		//		if (table.getRowCount()==0 && table.getColumnCount()==0){
		//			btnMreasoner.setEnabled(true);
		//			btnArgumentationSystem.setEnabled(false); 
		//			
		//		}else
		//		{
		//			btnArgumentationSystem.setEnabled(true); 
		//			btnMreasoner.setEnabled(false); 
		//		}
		//					
		//			}
		//		});
		//		
		//		btnLoadPotentialConflict.setBounds(580, 22, 190, 23);
		//		frame.getContentPane().add(btnLoadPotentialConflict);

		//		JLabel lblResolvedDetails = new JLabel("Click for Details of Resolved conflict....  ");
		//		lblResolvedDetails.setBounds(503, 207, 267, 61);
		//		frame.getContentPane().add(lblResolvedDetails);

		JTextArea jtaResolvedDetails = new JTextArea("Click Cells for Details of Solved conflict...");
		jtaResolvedDetails.setBounds(497, 190, 318, 55);
		frame.getContentPane().add(jtaResolvedDetails);


		JButton btnArgumentationSystem = new JButton("Argumentation Solver");
		btnArgumentationSystem.setBounds(923, 190, 180, 49);
		frame.getContentPane().add(btnArgumentationSystem);
		btnArgumentationSystem.setEnabled(false);

		JButton btnMreasoner = new JButton("MReasoner");
		btnMreasoner.setBounds(213, 190, 180, 49);
		frame.getContentPane().add(btnMreasoner);
		btnMreasoner.setEnabled(false);


		JButton btnLoadPotentialConflict = new JButton("Select Specification File");
		btnLoadPotentialConflict.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				conn = new MySQL_ArgumentationDBOperations();
				conn.connect();
				conn.erasePossibleConflicts();
//				conn.disconnect();
				
				JFileChooser ruleFile = new JFileChooser();
				int returnVal = ruleFile.showOpenDialog(btnLoadPotentialConflict);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = ruleFile.getSelectedFile();
					mainFile = file.getAbsolutePath();
					
					System.out.println("File path from Hybrid: " +mainFile);
				} else {
					System.out.println("Problem accessing file path or No File Selected");
				}
				
				try {
					RulesCompiler.checkConflictsFromFile(mainFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
													
				conn = new MySQL_ArgumentationDBOperations();
				conn.connect();
				ResultSet rs = conn.getPossibleConflicts();
				table.setModel(DbUtils.resultSetToTableModel(rs));
//				conn.disconnect();

				if (table.getRowCount() !=0 && table.getColumnCount() !=0)
				{
					btnMreasoner.setEnabled(false);
					btnArgumentationSystem.setEnabled(true);
				}

				if (table.getRowCount()<=0){
					btnMreasoner.setEnabled(true);
					btnArgumentationSystem.setEnabled(false);
				}
			}

		});

		btnLoadPotentialConflict.setBounds(520, 20, 290, 28);
		//btnLoadPotentialConflict.setBounds(580, 22, 190, 23);
		frame.getContentPane().add(btnLoadPotentialConflict);


		JButton btnLoadResults = new JButton("Load Results/Solved Conflicts");


		btnLoadResults.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

//				conn = new MySQL_ArgumentationDBOperations();
//				conn.connect();
				ResultSet rs =conn.getResultsTableContentPG();
//				ResultSet rs =conn.getResolvedConflicts();
				table_1.setModel(DbUtils.resultSetToTableModel(rs));
				table_1.setDefaultRenderer(Object.class, new ColorRenderer());

				table_1.addMouseListener(new java.awt.event.MouseAdapter() {
					@Override
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						int row = table_1.rowAtPoint(evt.getPoint());
						int col = table_1.columnAtPoint(evt.getPoint());
						if (row >= 0 && col >= 0) {
							ResultSet rs2 = conn.getResolvedConflicts(row-1);

							try {
								ResultSetMetaData rsmd = rs2.getMetaData();
								int columnsNumber = rsmd.getColumnCount();
								Vector<Integer> columnIndexes = new Vector<>();
								Vector<String> reasons = new Vector<>();
								String finalReason = "";

								while(rs2.next()){
									for(int i=4;i<columnsNumber;i++){
										String a = rs2.getString(i);
										if(a != null){
											columnIndexes.add(i);
											reasons.add(rs2.getString(columnsNumber));
										}
									}
								}

								boolean include = false;

								for(int i=0;i<columnIndexes.size();i++){
									if(columnIndexes.get(i) == col + 2){
										include = true;
										finalReason = reasons.get(i);
										break;
									}
								}

								if(include){
									String answer = "This detected conflict was solved using ";
									//Font answer = new Font ("This detected conflict was resolved using ", Font.BOLD, 20);
									//Font font = new Font ("Serif", Font.BOLD, 15);
									///Font source = font.getFont(finalReason);
									//jtaResolvedDetails.setText(answer.toString() + source);
									jtaResolvedDetails.setText(answer.toString() + finalReason);
								}else{
									jtaResolvedDetails.setText("No conflict detected here!");
									}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});

				//lblResolvedDetails.setText(model.getValueAt(row, col, 0).toString();)
//				conn.disconnect();
			}
		});

		btnLoadResults.setBounds(551, 292, 239, 42);
		frame.getContentPane().add(btnLoadResults);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(319, 345, 718, 258);
		frame.getContentPane().add(scrollPane_1);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);

		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setBounds(408, 1836, 492, 81);
		frame.getContentPane().add(textArea);
		//textArea.append(str); //setText(ResolvedReason2.getResolvedReason());

		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnMreasoner, btnArgumentationSystem, panel, scrollPane, table, btnLoadPotentialConflict}));

		btnMreasoner.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {	
				//String session = "C:/Users/Chimezie/hybrid-specification-files/HybridSessionPostgreSQL";
				String session = "C:/Reasoners/hybrid-specification-files/HybridSessionPostgreSQL";
				Main.main(new String[] {HybridMain.mainFile, session}); //pass a parameter of the file path
				//controller1.setDividersAtDefaultLocation();
			}
		});
		btnArgumentationSystem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {			
				//String session = "C:/Users/Chimezie/hybrid-specification-files/HybridSessionPostgreSQL";
				String session = "C:/Reasoners/hybrid-specification-files/HybridSessionPostgreSQL";
//				String session = "C:/Users/Chimezie/RealAI-JoseLeo/MReasonerLatest_Fast-AI/mreasoner-gui/mreasoner-gui/examples/development_stage/MySQL_padandlamp_realenvironment/postgresSession";
				Main.main(new String[] {HybridMain.mainFile, session});
				//controller.setDividersAtDefaultLocation();
			}

			
		});	

	}


	public class ColorRenderer extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table_1, Object value, boolean isSelected, boolean hasFocus, int row, int col)  {
			// get the DefaultCellRenderer to give you the basic component
			Component c = super.getTableCellRendererComponent(table_1, value, isSelected, hasFocus, row, col);
			// apply your rules

			ResultSet rs1 = conn.getResolvedConflicts(row-1);

			try {
				ResultSetMetaData rsmd = rs1.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				Vector<Integer> columnIndexes = new Vector<>();
				while(rs1.next()){
					for(int i=4;i<columnsNumber;i++){
						String a = rs1.getString(i);
						if(a != null){
							columnIndexes.add(i);
						}
					}
				}

				boolean include = false;

				for(int i=0;i<columnIndexes.size();i++){
					if(columnIndexes.get(i) == col + 2){
						include = true;
						break;
					}
				}
				if(include){
					c.setBackground(Color.YELLOW);
				}else{
					c.setBackground(table_1.getBackground());
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return c;
		}  
	}

}
