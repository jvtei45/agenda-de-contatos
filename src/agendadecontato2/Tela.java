/**
 *
 * @author Taylor & João Vitor Teixeira
 */
package agendadecontato2;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Tela extends javax.swing.JFrame {

    private ArrayList<Contato> listaContatos;
    private String estado = "Navegacao";
    DefaultTableModel tableModel;

    public Tela() {
        initComponents();
        listaContatos = new ArrayList<>();

        tableModel = new DefaultTableModel();
        TbRegistros.setModel(tableModel);

        tableModel.addColumn("Nome");
        tableModel.addColumn("Tipo de Contato");
        tableModel.addColumn("Favorito");
        tableModel.addColumn("Código");

    }

    private void setEnable(boolean ativar) {
        btnEditar.setEnabled(ativar);
        btnExcluir.setEnabled(ativar);
        btnInserir.setEnabled(ativar);
        btnGravar.setEnabled(!ativar);
        btnCancelar.setEnabled(!ativar);

        txtName.setEnabled(!ativar);
        txtTel.setEnabled(!ativar);
        txtFax.setEnabled(!ativar);
        txtCel.setEnabled(!ativar);
        txtNomeEmp.setEnabled(!ativar);
        txtCargoEmp.setEnabled(!ativar);
        ckFavoritos.setEnabled(!ativar);
        cbTipoContato.setEnabled(!ativar);
        textObs.setEnabled(!ativar);

    }

    private void limparTela() {
        txtName.setText("");
        txtTel.setText("");
        txtCel.setText("");
        txtFax.setText("");
        txtNomeEmp.setText("");
        txtCargoEmp.setText("");
        textObs.setText("");
        ckFavoritos.setSelected(false);
        cbTipoContato.setSelectedIndex(0);
    }

    private void addInTable(Contato contato) {
        DefaultTableModel table = (DefaultTableModel) TbRegistros.getModel();

        Object[] linha = new Object[table.getColumnCount()];

        if (estado.equals("Editar")) {
            table.setValueAt(contato.getNome(), TbRegistros.getSelectedRow(), 0);
            table.setValueAt(contato.getTipoContato(), TbRegistros.getSelectedRow(), 1);
            table.setValueAt(contato.isIsFavorite(), TbRegistros.getSelectedRow(), 2);
            table.setValueAt(contato.getCodigo(), TbRegistros.getSelectedRow(), 3);
        } else {

            linha[0] = contato.getNome();
            linha[1] = contato.getTipoContato();
            linha[2] = contato.isIsFavorite();
            linha[3] = contato.getCodigo();

            table.addRow(linha);
        }
    }

    private void preencherFormulario(boolean isEnabled, Contato contact) {
        if (listaContatos == null) {
            JOptionPane.showMessageDialog(appBar, "Selecione um contato!!!", "Erro de Seleção", JOptionPane.ERROR_MESSAGE);
            setEnable(true);
        } else if (!estado.equalsIgnoreCase("editar")) {
            txtName.setText(contact.getNome());
            txtTel.setText(contact.getTelefone());
            txtCel.setText(contact.getCelular());
            txtFax.setText(contact.getFax());
            ckFavoritos.setSelected(contact.isIsFavorite());
            cbTipoContato.setSelectedItem(contact.getTipoContato());
            textObs.setText(contact.getObservacao());
            setEnable(isEnabled);
        }

        TbRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TbRegistrosMouseClicked(evt);
            }
        });

    }

    private void GravarArquivoXML() {
        try {
            XStream st = new XStream(new DomDriver());
            st.alias("Contatos", List.class);
            st.alias("Contato", Contato.class);

            File Arquivo = new File("Contatos.xml");

            FileOutputStream ArqXML = new FileOutputStream(Arquivo);
            ArqXML.write(st.toXML(listaContatos).getBytes());
            ArqXML.close();
        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "teste");
        }
    }

    private void CarregarDados() {
        try {
            XStream st = new XStream(new DomDriver());
            st.addPermission(PrimitiveTypePermission.PRIMITIVES);
            st.allowTypes(new Class[]{Contato.class, List.class, ArrayList.class});
            st.alias("Contatos", List.class);
            st.alias("Contato", Contato.class);

            BufferedReader ArqXML = new BufferedReader(new FileReader("Contatos.xml"));
            listaContatos = (ArrayList<Contato>) st.fromXML(ArqXML);
            ArqXML.close();

            for (int i = 0; i < listaContatos.size(); i++) {
                tableModel.addRow(new Object[]{
                    listaContatos.get(i).getNome(),
                    listaContatos.get(i).getCelular(),
                    listaContatos.get(i).isIsFavorite(),
                    listaContatos.get(i).getCodigo()
                });
            }

            ArqXML.close();

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, E.getMessage(), "Erro na leitura do XML", JOptionPane.ERROR_MESSAGE);
        }

    }

    private Contato getContatoModel(int rowIndex) {
        DefaultTableModel model = (DefaultTableModel) TbRegistros.getModel();
        String codigoContato = model.getValueAt(rowIndex, 3).toString();

        for (Contato contato : listaContatos) {
            if (contato.getCodigo().equals(codigoContato)) {
                return contato;
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        appBar = new javax.swing.JPanel();
        textAppBar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        contatosPanel = new javax.swing.JPanel();
        lbName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lbTipoContato = new javax.swing.JLabel();
        cbTipoContato = new javax.swing.JComboBox<>();
        ckFavoritos = new javax.swing.JCheckBox();
        panelTelefones = new javax.swing.JPanel();
        lbTel = new javax.swing.JLabel();
        txtTel = new javax.swing.JTextField();
        lbCel = new javax.swing.JLabel();
        txtCel = new javax.swing.JTextField();
        lbFax = new javax.swing.JLabel();
        txtFax = new javax.swing.JTextField();
        panelObs = new javax.swing.JPanel();
        scrollObs = new javax.swing.JScrollPane();
        textObs = new javax.swing.JTextPane();
        panelEmp = new javax.swing.JPanel();
        lbNomeEmp = new javax.swing.JLabel();
        txtNomeEmp = new javax.swing.JTextField();
        lbCargoEmp = new javax.swing.JLabel();
        txtCargoEmp = new javax.swing.JTextField();
        panelTable = new javax.swing.JPanel();
        scrollTable = new javax.swing.JScrollPane();
        TbRegistros = new javax.swing.JTable();
        btnInserir = new javax.swing.JButton();
        btnGravar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Agenda de Contatos ");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        appBar.setBackground(new java.awt.Color(0, 102, 102));
        appBar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                appBarPropertyChange(evt);
            }
        });

        textAppBar.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        textAppBar.setForeground(new java.awt.Color(255, 255, 255));
        textAppBar.setText("Agenda de Contatos");

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        contatosPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contatos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        contatosPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contatosPanelMouseClicked(evt);
            }
        });

        lbName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbName.setText("Nome");

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        lbTipoContato.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbTipoContato.setText("Tipo de Contato");

        cbTipoContato.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbTipoContato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pessoal", "Profissional", "Outros" }));
        cbTipoContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoContatoActionPerformed(evt);
            }
        });

        ckFavoritos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ckFavoritos.setText("Favoritos");

        panelTelefones.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Telefones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        lbTel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbTel.setText("Telefone");

        lbCel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbCel.setText("Celular");

        lbFax.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbFax.setText("Fax");

        javax.swing.GroupLayout panelTelefonesLayout = new javax.swing.GroupLayout(panelTelefones);
        panelTelefones.setLayout(panelTelefonesLayout);
        panelTelefonesLayout.setHorizontalGroup(
            panelTelefonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTel)
            .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lbCel)
            .addComponent(txtCel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lbFax)
            .addComponent(txtFax, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelTelefonesLayout.setVerticalGroup(
            panelTelefonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTelefonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbCel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbFax)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelObs.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Observação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        textObs.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        scrollObs.setViewportView(textObs);

        javax.swing.GroupLayout panelObsLayout = new javax.swing.GroupLayout(panelObs);
        panelObs.setLayout(panelObsLayout);
        panelObsLayout.setHorizontalGroup(
            panelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelObsLayout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(scrollObs, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
        );
        panelObsLayout.setVerticalGroup(
            panelObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollObs)
        );

        panelEmp.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Empresa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        lbNomeEmp.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbNomeEmp.setText("Nome");

        lbCargoEmp.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbCargoEmp.setText("Cargo");

        javax.swing.GroupLayout panelEmpLayout = new javax.swing.GroupLayout(panelEmp);
        panelEmp.setLayout(panelEmpLayout);
        panelEmpLayout.setHorizontalGroup(
            panelEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNomeEmp)
                    .addComponent(txtCargoEmp)
                    .addGroup(panelEmpLayout.createSequentialGroup()
                        .addGroup(panelEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNomeEmp)
                            .addComponent(lbCargoEmp))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelEmpLayout.setVerticalGroup(
            panelEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmpLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lbNomeEmp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbCargoEmp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCargoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelTable.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        TbRegistros.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        TbRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Contato", "Favorito", "Codigo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TbRegistros.setEnabled(false);
        TbRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TbRegistrosMouseClicked(evt);
            }
        });
        scrollTable.setViewportView(TbRegistros);

        javax.swing.GroupLayout panelTableLayout = new javax.swing.GroupLayout(panelTable);
        panelTable.setLayout(panelTableLayout);
        panelTableLayout.setHorizontalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelTableLayout.setVerticalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnInserir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnInserir.setText("Inserir");
        btnInserir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInserirMouseClicked(evt);
            }
        });
        btnInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirActionPerformed(evt);
            }
        });

        btnGravar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGravar.setText("Gravar");
        btnGravar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGravarMouseClicked(evt);
            }
        });
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contatosPanelLayout = new javax.swing.GroupLayout(contatosPanel);
        contatosPanel.setLayout(contatosPanelLayout);
        contatosPanelLayout.setHorizontalGroup(
            contatosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contatosPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(201, 201, 201))
            .addGroup(contatosPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(contatosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contatosPanelLayout.createSequentialGroup()
                        .addGroup(contatosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contatosPanelLayout.createSequentialGroup()
                                .addComponent(panelTelefones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(panelObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbName)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(contatosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contatosPanelLayout.createSequentialGroup()
                                .addGroup(contatosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbTipoContato)
                                    .addComponent(cbTipoContato, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 41, Short.MAX_VALUE)
                                .addComponent(ckFavoritos)
                                .addGap(72, 72, 72))
                            .addGroup(contatosPanelLayout.createSequentialGroup()
                                .addComponent(panelEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addComponent(panelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        contatosPanelLayout.setVerticalGroup(
            contatosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contatosPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(contatosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbName)
                    .addComponent(lbTipoContato))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contatosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckFavoritos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contatosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelObs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTelefones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(panelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(contatosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jScrollPane1.setViewportView(contatosPanel);

        javax.swing.GroupLayout appBarLayout = new javax.swing.GroupLayout(appBar);
        appBar.setLayout(appBarLayout);
        appBarLayout.setHorizontalGroup(
            appBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, appBarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textAppBar)
                .addGap(292, 292, 292))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        appBarLayout.setVerticalGroup(
            appBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appBarLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(textAppBar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(appBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(appBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed

    }//GEN-LAST:event_txtNameActionPerformed

    private void cbTipoContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoContatoActionPerformed
        if (cbTipoContato.getSelectedItem().equals("Profissional")) {
            panelEmp.setVisible(true);
        } else {
            panelEmp.setVisible(false);
        }
    }//GEN-LAST:event_cbTipoContatoActionPerformed

    private void TbRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TbRegistrosMouseClicked

        int selectedRow = TbRegistros.getSelectedRow();
        if (selectedRow >= 0) {

        }


    }//GEN-LAST:event_TbRegistrosMouseClicked

    private void btnInserirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInserirMouseClicked

    }//GEN-LAST:event_btnInserirMouseClicked

    private void btnInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirActionPerformed
        txtName.requestFocus();
        TbRegistros.clearSelection();
        limparTela();
        estado = "Inserir";
        setEnable(false);
    }//GEN-LAST:event_btnInserirActionPerformed

    private void btnGravarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGravarMouseClicked

    }//GEN-LAST:event_btnGravarMouseClicked

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        if (estado.equals("Editar")) {
            int selectedRow = TbRegistros.getSelectedRow();

            if (selectedRow != -1) {

                int selectedIndex = TbRegistros.getSelectedRow();

                Contato contatoSelecionado = listaContatos.get(selectedIndex);

                contatoSelecionado.setNome(txtName.getText());
                contatoSelecionado.setTelefone(txtTel.getText());

                DefaultTableModel model = (DefaultTableModel) TbRegistros.getModel();
                model.setValueAt(contatoSelecionado.getNome(), selectedIndex, 0);
                model.setValueAt(contatoSelecionado.getTipoContato(), selectedIndex, 1);
                model.setValueAt(contatoSelecionado.isIsFavorite() ? "Sim" : "Não", selectedIndex, 2);
                model.setValueAt(contatoSelecionado.getCodigo(), selectedIndex, 3);

                limparTela();

                estado = "Navegacao";
                setEnable(true); // Habilita os controles
            }
        } else {

            Contato novoContato = new Contato(
                    txtName.getText(),
                    txtTel.getText(),
                    txtFax.getText(),
                    txtCel.getText(),
                    textObs.getText(),
                    cbTipoContato.getSelectedItem().toString(),
                    ckFavoritos.isSelected()
            );

            listaContatos.add(novoContato);

            DefaultTableModel model = (DefaultTableModel) TbRegistros.getModel();
            Object[] rowData = {
                novoContato.getNome(),
                novoContato.getTipoContato(),
                novoContato.isIsFavorite() ? "Sim" : "Não",
                novoContato.getCodigo()
            };
            model.addRow(rowData);

            limparTela();
        }
    }//GEN-LAST:event_btnGravarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        setEnable(true);
        limparTela();
        TbRegistros.setEnabled(true);
        TbRegistros.clearSelection();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int selectedRow = TbRegistros.getSelectedRow();

        if (selectedRow != -1) {

            int selectedIndex = TbRegistros.getSelectedRow();

            Contato contatoSelecionado = listaContatos.get(selectedIndex);

            txtName.setText(contatoSelecionado.getNome());
            txtTel.setText(contatoSelecionado.getTelefone());
            txtFax.setText(contatoSelecionado.getFax());
            txtCel.setText(contatoSelecionado.getCelular());
            textObs.setText(contatoSelecionado.getObservacao());
            cbTipoContato.setSelectedItem(contatoSelecionado.getTipoContato());
            ckFavoritos.setSelected(contatoSelecionado.isIsFavorite());

            setEnable(false);

            estado = "Editar";
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um contato para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed

        int selectedRow = TbRegistros.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um contato para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String codigoContato = TbRegistros.getValueAt(selectedRow, 3).toString();

        Contato contatoParaRemover = null;
        for (Contato contato : listaContatos) {
            if (contato.getCodigo().equals(codigoContato)) {
                contatoParaRemover = contato;
                break;
            }
        }

        if (contatoParaRemover != null) {
            listaContatos.remove(contatoParaRemover);
        }

        DefaultTableModel model = (DefaultTableModel) TbRegistros.getModel();
        model.removeRow(selectedRow);

        limparTela();

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void contatosPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contatosPanelMouseClicked
        TbRegistros.clearSelection();
    }//GEN-LAST:event_contatosPanelMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        GravarArquivoXML();
    }//GEN-LAST:event_formWindowClosing

    private void appBarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_appBarPropertyChange

    }//GEN-LAST:event_appBarPropertyChange

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        CarregarDados();
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela().setVisible(true);

            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TbRegistros;
    private javax.swing.JPanel appBar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnInserir;
    private javax.swing.JComboBox<String> cbTipoContato;
    private javax.swing.JCheckBox ckFavoritos;
    private javax.swing.JPanel contatosPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCargoEmp;
    private javax.swing.JLabel lbCel;
    private javax.swing.JLabel lbFax;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbNomeEmp;
    private javax.swing.JLabel lbTel;
    private javax.swing.JLabel lbTipoContato;
    private javax.swing.JPanel panelEmp;
    private javax.swing.JPanel panelObs;
    private javax.swing.JPanel panelTable;
    private javax.swing.JPanel panelTelefones;
    private javax.swing.JScrollPane scrollObs;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JLabel textAppBar;
    private javax.swing.JTextPane textObs;
    private javax.swing.JTextField txtCargoEmp;
    private javax.swing.JTextField txtCel;
    private javax.swing.JTextField txtFax;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNomeEmp;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables

}
