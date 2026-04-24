package material.juego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class JavaCodeViewer extends JFrame {

    private JTextPane textPane;
    private StyledDocument doc;

    private Style normalStyle;
    private Style keywordStyle;
    private Style stringStyle;
    private Style commentStyle;

    public JavaCodeViewer() {
        setTitle("Java Code Viewer");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textPane = new JTextPane();
        textPane.setFont(new Font("Consolas", Font.PLAIN, 15));
        textPane.setEditable(false);

        doc = textPane.getStyledDocument();
        initStyles();

        JScrollPane scroll = new JScrollPane(textPane);
        add(scroll, BorderLayout.CENTER);

        // Menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        JMenuItem abrir = new JMenuItem("Abrir .java...");

        abrir.addActionListener(e -> abrirArchivo());

        menu.add(abrir);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void initStyles() {
        StyleContext sc = StyleContext.getDefaultStyleContext();

        normalStyle = sc.addStyle("NORMAL", null);
        StyleConstants.setForeground(normalStyle, Color.BLACK);

        keywordStyle = sc.addStyle("KEYWORD", null);
        StyleConstants.setForeground(keywordStyle, new Color(127, 0, 85));
        StyleConstants.setBold(keywordStyle, true);

        stringStyle = sc.addStyle("STRING", null);
        StyleConstants.setForeground(stringStyle, new Color(42, 0, 255));

        commentStyle = sc.addStyle("COMMENT", null);
        StyleConstants.setForeground(commentStyle, new Color(63, 127, 95));
        StyleConstants.setItalic(commentStyle, true);
    }

    private void abrirArchivo() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccionar archivo Java");
        chooser.setFileFilter(new FileNameExtensionFilter("Archivos Java (*.java)", "java"));

        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File archivo = chooser.getSelectedFile();
            cargarArchivo(archivo);
        }
    }

    private void cargarArchivo(File archivo) {
        try {
            String content = Files.readString(archivo.toPath());
            setCode(content);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al leer el archivo",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setCode(String code) {
        try {
            doc.remove(0, doc.getLength());
            doc.insertString(0, code, normalStyle);
            applyHighlighting();
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void applyHighlighting() {
        String text = textPane.getText();
        doc.setCharacterAttributes(0, text.length(), normalStyle, true);

        // Keywords
        String[] keywords = {
                "public", "class", "static", "void", "int", "double",
                "if", "else", "for", "while", "return", "new", "String",
                "private", "protected"
        };

        for (String kw : keywords) {
            Pattern p = Pattern.compile("\\b" + kw + "\\b");
            Matcher m = p.matcher(text);
            while (m.find()) {
                doc.setCharacterAttributes(m.start(), m.end() - m.start(), keywordStyle, true);
            }
        }

        // Strings
        Pattern pStrings = Pattern.compile("\"(\\\\.|[^\"])*\"");
        Matcher mStrings = pStrings.matcher(text);
        while (mStrings.find()) {
            doc.setCharacterAttributes(mStrings.start(), mStrings.end() - mStrings.start(), stringStyle, true);
        }

        // Comentarios //
        Pattern pComments = Pattern.compile("//.*");
        Matcher mComments = pComments.matcher(text);
        while (mComments.find()) {
            doc.setCharacterAttributes(mComments.start(), mComments.end() - mComments.start(), commentStyle, true);
        }

        // Comentarios /* */
        Pattern pCommentsMulti = Pattern.compile("/\\*(.|\\R)*?\\*/");
        Matcher mCommentsMulti = pCommentsMulti.matcher(text);
        while (mCommentsMulti.find()) {
            doc.setCharacterAttributes(mCommentsMulti.start(), mCommentsMulti.end() - mCommentsMulti.start(), commentStyle, true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JavaCodeViewer().setVisible(true));
    }
}

