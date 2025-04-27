import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SimaGUI extends JFrame {
    private JComboBox<String> productCombo;
    private JTextField priceField;
    private JButton generateButton;
    private JLabel pitchLabel;
    private JLabel characterLabel;
    private JButton yesButton;
    private JButton noButton;
    private JTextArea resultArea;

    private String selectedPitch = "";
    private String selectedCharacter = "";
    private String selectedProduct = "";
    private double enteredPrice = 0;

    private static final String[] pitches = {
        "منتج مصمم خصيصًا لمحبي التميز",
        "الخيار المثالي لكل شخص عملي",
        "مستوحى من التراث العماني بلمسة عصرية",
        "جودة عالية وسعر منافس",
        "يجعل حياتك أسهل ويعبر عن ذوقك",
        "صنع بحب لذوي الذوق الرفيع",
        "يرفع من كفاءتك اليومية",
        "اختيار النخبة",
        "خيار ذكي لرواد المستقبل"
    };

    private static final String[] characters = {
        "شخص يرتدي نظارة",
        "طالبة تهتم بالمظهر",
        "شخص يحب التنظيم",
        "مصوّر هاوٍ",
        "محاضر جامعي",
        "شخص كلاسيكي التفكير",
        "مراهق عصري",
        "موظف في قسم التسويق",
        "شخص يهوى التراث العماني"
    };

    public SimaGUI() {
        setTitle("سِمة");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLayout(new FlowLayout());

        productCombo = new JComboBox<>(new String[]{
            "كاميرا فورية", "آلة طباعة يدوية", "منتج مكياج", "كمة عمانية"
        });
        priceField = new JTextField(10);
        generateButton = new JButton("تم");

        pitchLabel = new JLabel("الجملة التسويقية: ");
        characterLabel = new JLabel("الشخصية المستهدفة: ");

        yesButton = new JButton("نعم");
        noButton = new JButton("لا");
        yesButton.setEnabled(false);
        noButton.setEnabled(false);

        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        add(new JLabel("اختر منتجك:"));
        add(productCombo);
        add(new JLabel("اكتب السعر بالريال العماني:"));
        add(priceField);
        add(generateButton);
        add(pitchLabel);
        add(characterLabel);
        add(yesButton);
        add(noButton);
        add(new JScrollPane(resultArea));

        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateScenario();
            }
        });

        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sell(true);
            }
        });

        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sell(false);
            }
        });
    }

    private void generateScenario() {
        try {
            enteredPrice = Double.parseDouble(priceField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "يرجى إدخال سعر صحيح.", "خطأ", JOptionPane.ERROR_MESSAGE);
            return;
        }

        selectedProduct = (String) productCombo.getSelectedItem();
        selectedPitch = getRandom(pitches);
        selectedCharacter = getRandom(characters);

        pitchLabel.setText("الجملة التسويقية: " + selectedPitch);
        characterLabel.setText("الشخصية المستهدفة: " + selectedCharacter);

        yesButton.setEnabled(true);
        noButton.setEnabled(true);

        resultArea.setText("");
    }

    private void sell(boolean agreed) {
        StringBuilder result = new StringBuilder();
        result.append("المنتج: ").append(selectedProduct).append("\n");
        result.append("السعر: ").append(enteredPrice).append(" ريال\n");
        result.append("الجملة التسويقية: ").append(selectedPitch).append("\n");
        result.append("الشخصية: ").append(selectedCharacter).append("\n\n");

        if (agreed) {
            result.append("تمت عملية البيع بنجاح!\n\n");
            if (enteredPrice <= 5) {
                result.append("تحليل الشخصية: أنت بائع سريع الذكاء، تفضل الانتشار أكثر من الربح، مما يجعلك مؤثرًا على المدى الطويل.");
            } else if (enteredPrice <= 10) {
                result.append("تحليل الشخصية: شخصيتك متوازنة وتعرف قيمة المنتج والسوق. أنت بارع في تقديم حلول واقعية ومقنعة.");
            } else {
                result.append("تحليل الشخصية: واثق وطموح، تميل للتسويق الفاخر وتؤمن أن الجودة تستحق السعر. استمر وطور أدوات الإقناع.");
            }
        } else {
            result.append("لم تتم عملية البيع.\n\n");
            if (enteredPrice > 10) {
                result.append("تحليل الشخصية: ربما كان السعر مرتفعًا جدًا لهذه الشخصية. الموازنة بين الطموح واحتياجات العميل ضرورية.");
            } else {
                result.append("تحليل الشخصية: تحتاج لتحسين طريقة تقديمك للمنتج أو اختيار أفضل توقيت للمحاولة.");
            }
            result.append("\nتذكر: كل محاولة تقربك من الإتقان، جرب من جديد بثقة!");
        }

        resultArea.setText(result.toString());

        yesButton.setEnabled(false);
        noButton.setEnabled(false);
    }

    private String getRandom(String[] arr) {
        Random random = new Random();
        return arr[random.nextInt(arr.length)];
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimaGUI().setVisible(true);
            }
        });
    }
}
