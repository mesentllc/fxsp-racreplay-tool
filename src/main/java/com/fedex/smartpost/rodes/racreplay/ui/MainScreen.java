/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fedex.smartpost.rodes.racreplay.ui;

import com.fedex.smartpost.rodes.racreplay.SystemParameter;
import com.fedex.smartpost.rodes.racreplay.enums.PFEnvironment;
import com.fedex.smartpost.rodes.racreplay.utils.ReplayUtility;
import com.fedex.smartpost.rodes.racreplay.utils.SystemParameterUtilities;
import javax.swing.JOptionPane;

/**
 *
 * @author 796812
 */
public class MainScreen extends javax.swing.JFrame {
	private int updateSystemParameter(SystemParameter systemParameter) {
		return SystemParameterUtilities.update(PFEnvironment.valueOf((String)cbxEnv.getSelectedItem()), 
											   systemParameter);
	}
	
	private int executeReplay() {
		return 0;
	}
	
	public MainScreen() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEnv = new javax.swing.JLabel();
        cbxEnv = new javax.swing.JComboBox();
        lblRAC = new javax.swing.JLabel();
        txtRAC = new javax.swing.JTextField();
        btnRun = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblEnv.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblEnv.setText("Environment:");

        cbxEnv.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "L0", "L2", "L3", "L4", "PRD" }));

        lblRAC.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblRAC.setText("RAC Id:");

        btnRun.setText("Execute");
        btnRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEnv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxEnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblRAC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRAC))
                    .addComponent(btnRun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEnv)
                    .addComponent(cbxEnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRAC)
                    .addComponent(txtRAC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRun)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunActionPerformed
        SystemParameter systemParameter = new SystemParameter();
		boolean failed = false;
		
		systemParameter.setName("RatingFileSender.RatingFileScript.Active");
		systemParameter.setCache("NONE");
		systemParameter.setCategory("RODES");
		systemParameter.setComment("Toggles the old rating script execution.");
		systemParameter.setSubCategory("RATING");
		systemParameter.setType("BOOLEAN");
		systemParameter.setValue("true");
		if (updateSystemParameter(systemParameter) != 0) {
			JOptionPane.showMessageDialog(this, "Can't enable rating script.");
			return;
		}
		if (!ReplayUtility.replay(PFEnvironment.valueOf((String)cbxEnv.getSelectedItem()), txtRAC.getText())) {
			JOptionPane.showMessageDialog(this, "Replay failed.");
			failed = true;
		}
		systemParameter.setValue("false");
		if (updateSystemParameter(systemParameter) != 0) {
			JOptionPane.showMessageDialog(this, "Can't disable rating script.");
			return;
		}
		if (!failed) {
			JOptionPane.showMessageDialog(this, txtRAC + " replayed.");
		}
    }//GEN-LAST:event_btnRunActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
        //</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainScreen().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRun;
    private javax.swing.JComboBox cbxEnv;
    private javax.swing.JLabel lblEnv;
    private javax.swing.JLabel lblRAC;
    private javax.swing.JTextField txtRAC;
    // End of variables declaration//GEN-END:variables
}
