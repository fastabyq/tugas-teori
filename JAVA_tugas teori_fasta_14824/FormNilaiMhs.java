import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class FormNilaiMhs extends JFrame {
    private DefaultTableModel tabel1;
    private Db dbConn = new Db();
    private Connection con = dbConn.getConnection();
    private JTextField nim, nama, nUTS, nUAS, nTugas, nUTS1, nUAS1, nTugas1, nUTS2, nUAS2, nTugas2;
    private JButton simpan, proses1, hapus, update, tambahLain;
    private JTable tabel;

    public FormNilaiMhs() throws SQLException {
        initComponents();
        tabel1 = new DefaultTableModel();
        tabel.setModel(tabel1);
        tabel1.addColumn("No");
        tabel1.addColumn("Nim");
        tabel1.addColumn("Nama");
        tabel1.addColumn("Nilai Uts");
        tabel1.addColumn("Nilai Uas");
        tabel1.addColumn("Nilai Tugas");
        tampilkanDiTabel();
    }

    private void initComponents() {
        setTitle("Form Nilai Mahasiswa");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        nim = new JTextField(10);
        nama = new JTextField(20);
        nUTS = new JTextField(5);
        nUAS = new JTextField(5);
        nTugas = new JTextField(5);
        nUTS1 = new JTextField(5);
        nUTS1.setEditable(false);
        nUAS1 = new JTextField(5);
        nUAS1.setEditable(false);
        nTugas1 = new JTextField(5);
        nTugas1.setEditable(false);
        nUTS2 = new JTextField(5);
        nUTS2.setEditable(false);
        nUAS2 = new JTextField(5);
        nUAS2.setEditable(false);
        nTugas2 = new JTextField(5);
        nTugas2.setEditable(false);

        simpan = new JButton("Simpan");
        proses1 = new JButton("Proses");
        hapus = new JButton("Hapus");
        update = new JButton("Update");
        tambahLain = new JButton("Tambah Lain");
        tabel = new JTable();

        JScrollPane scrollPane = new JScrollPane(tabel);

        gbc.gridx = 0; gbc.gridy = 0; inputPanel.add(new JLabel("NIM:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; inputPanel.add(nim, gbc);
        gbc.gridx = 0; gbc.gridy = 1; inputPanel.add(new JLabel("Nama:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; inputPanel.add(nama, gbc);
        gbc.gridx = 0; gbc.gridy = 2; inputPanel.add(new JLabel("UTS:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; inputPanel.add(nUTS, gbc);
        gbc.gridx = 0; gbc.gridy = 3; inputPanel.add(new JLabel("UAS:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; inputPanel.add(nUAS, gbc);
        gbc.gridx = 0; gbc.gridy = 4; inputPanel.add(new JLabel("Tugas:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4; inputPanel.add(nTugas, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(proses1);
        buttonPanel.add(simpan);
        buttonPanel.add(hapus);
        buttonPanel.add(update);
        buttonPanel.add(tambahLain);

        JPanel outputPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0; gbc.gridy = 0; outputPanel.add(new JLabel("Nilai UTS:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; outputPanel.add(nUTS1, gbc);
        gbc.gridx = 0; gbc.gridy = 1; outputPanel.add(new JLabel("Nilai UAS:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; outputPanel.add(nUAS1, gbc);
        gbc.gridx = 0; gbc.gridy = 2; outputPanel.add(new JLabel("Nilai Tugas:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; outputPanel.add(nTugas1, gbc);
        gbc.gridx = 0; gbc.gridy = 3; outputPanel.add(new JLabel("Nilai Akhir:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; outputPanel.add(nUTS2, gbc);
        gbc.gridx = 0; gbc.gridy = 4; outputPanel.add(new JLabel("Nilai Huruf:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4; outputPanel.add(nUAS2, gbc);
        gbc.gridx = 0; gbc.gridy = 5; outputPanel.add(new JLabel("Predikat:"), gbc);
        gbc.gridx = 1; gbc.gridy = 5; outputPanel.add(nTugas2, gbc);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(outputPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        simpan.setEnabled(false);
        update.setEnabled(false);
        hapus.setEnabled(false);

        simpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanActionPerformed(e);
            }
        });

        proses1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proses1ActionPerformed(e);
            }
        });

        hapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusActionPerformed(e);
            }
        });

        tambahLain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahLainActionPerformed(e);
            }
        });

        tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
    }

    public void kosongkanTextField() {
        nim.setText("");
        nama.setText("");
        nUTS.setText("");
        nUAS.setText("");
        nTugas.setText("");
        nUTS1.setText("");
        nUAS1.setText("");
        nTugas1.setText("");
        nUTS2.setText("");
        nUAS2.setText("");
        nTugas2.setText("");
    }

    public void prosesHitungNilai() {
        try {
            String ni = nim.getText();
            String na = nama.getText();
            Double ts = Double.parseDouble(nUTS.getText());
            Double as = Double.parseDouble(nUAS.getText());
            Double tgs = Double.parseDouble(nTugas.getText());

            Mhs m = new Mhs(ni, na, ts, as, tgs);
            nUTS1.setText("" + m.uts());
            nUAS1.setText("" + m.uas());
            nTugas1.setText("" + m.tugas());
            nUTS2.setText("" + m.nilaiAkhir());
            nUAS2.setText("" + m.getNilHuruf(m.nilaiAkhir()));
            nTugas2.setText("" + m.getPredikat(m.getNilHuruf(m.nilaiAkhir())));
            simpan.setEnabled(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Inputan Anda Kosong", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public ArrayList[] getMhsList() throws SQLException {
        String queryCount = "SELECT COUNT(*) as c FROM mhs";
        Statement st;
        ResultSet rsCount;
        st = con.createStatement();
        rsCount = st.executeQuery(queryCount);
        int sizeTable = 0;

        while (rsCount.next()) {
            sizeTable = rsCount.getInt("c");
        }

        ArrayList[] mhsList = new ArrayList[sizeTable];
        String query = "SELECT * FROM mhs";
        ResultSet rs = st.executeQuery(query);
        int index = 0;

        while (rs.next()) {
            ArrayList<Object> row = new ArrayList<>();
            row.add(rs.getString("nim"));
            row.add(rs.getString("nama"));
            row.add(rs.getDouble("nilai_uts"));
            row.add(rs.getDouble("nilai_uas"));
            row.add(rs.getDouble("nilai_tugas"));
            mhsList[index] = row;
            index++;
        }
        return mhsList;
    }

    public void tampilkanDiTabel() throws SQLException {
        ArrayList[] list = getMhsList();
        DefaultTableModel model = (DefaultTableModel) tabel.getModel();

        Object[] row = new Object[6];
        for (int i = 0; i < list.length; i++) {
            row[0] = i + 1;
            row[1] = list[i].get(0);
            row[2] = list[i].get(1);
            row[3] = list[i].get(2);
            row[4] = list[i].get(3);
            row[5] = list[i].get(4);

            model.addRow(row);
        }
    }

    public void kosongkanTabel() {
        DefaultTableModel model = (DefaultTableModel) this.tabel.getModel();
        model.setRowCount(0);
    }

    private void simpanActionPerformed(ActionEvent evt) {
        try {
            String ni = nim.getText();
            String na = nama.getText();
            Double ts = Double.parseDouble(nUTS.getText());
            Double as = Double.parseDouble(nUAS.getText());
            Double tgs = Double.parseDouble(nTugas.getText());

            String query = "INSERT INTO mhs (nim, nama, nilai_uts, nilai_uas, nilai_tugas) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, ni);
            pst.setString(2, na);
            pst.setDouble(3, ts);
            pst.setDouble(4, as);
            pst.setDouble(5, tgs);
            pst.executeUpdate();

            kosongkanTabel();
            tampilkanDiTabel();
            kosongkanTextField();
            simpan.setEnabled(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void hapusActionPerformed(ActionEvent evt) {
        int row = tabel.getSelectedRow();
        if (row >= 0) {
            String nim = tabel.getValueAt(row, 1).toString();
            try {
                String query = "DELETE FROM mhs WHERE nim = ?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, nim);
                pst.executeUpdate();

                kosongkanTabel();
                tampilkanDiTabel();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void tambahLainActionPerformed(ActionEvent evt) {
        kosongkanTextField();
        simpan.setEnabled(false);
        update.setEnabled(false);
        hapus.setEnabled(false);
    }

    private void tabelMouseClicked(MouseEvent evt) {
        int row = tabel.getSelectedRow();
        nim.setText(tabel.getValueAt(row, 1).toString());
        nama.setText(tabel.getValueAt(row, 2).toString());
        nUTS.setText(tabel.getValueAt(row, 3).toString());
        nUAS.setText(tabel.getValueAt(row, 4).toString());
        nTugas.setText(tabel.getValueAt(row, 5).toString());
        simpan.setEnabled(false);
        update.setEnabled(true);
        hapus.setEnabled(true);
    }

    private void proses1ActionPerformed(ActionEvent evt) {
        prosesHitungNilai();
    }

    public static void main(String[] args) {
        try {
            FormNilaiMhs form = new FormNilaiMhs();
            form.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
