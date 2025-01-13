import javax.swing.*;

class encrypter {

    char[] convertedContent;
    static encrypter encrypter = new encrypter();
    int shiftAmount = 13;
    int[] collectedAscii;
    boolean encrypt, decrypt;

    
        public static void main(String[] args) {
            JLabel inputText = new JLabel("Input");
            JLabel outputText = new JLabel("Output");

            JFrame frame = new JFrame();
            JTextArea text = new JTextArea();
            JTextArea result = new JTextArea();
            JButton button = new JButton("Submit");
            JButton encrypt = new JButton("Encrypt");
            JButton decrypt = new JButton("Decrypt");
            
            //set up the positions for everything (e.g. buttons, frame, etc)
            inputText.setBounds(50, 10, 400, 30);
            outputText.setBounds(50, 310, 400, 30);
            text.setBounds(50, 50, 400, 150);
            result.setBounds(50, 350, 400, 150);
            button.setBounds(250, 250, 100, 50);
            encrypt.setBounds(50, 250, 100, 50);
            decrypt.setBounds(150, 250, 100, 50);

        button.addActionListener(e -> {
            String content = text.getText();
            encrypter.inputToArray(content, result); 
        });

        JLabel modeLabel = new JLabel("Mode: Encrypt");
        modeLabel.setBounds(200, 10, 200, 30);
        frame.add(modeLabel);
        
        encrypt.addActionListener(e -> {
            encrypter.encrypt = true;
            encrypter.decrypt = false;
            modeLabel.setText("Mode: Encrypt");
        });
        
        decrypt.addActionListener(e -> {
            encrypter.encrypt = false;
            encrypter.decrypt = true;
            modeLabel.setText("Mode: Decrypt");
        });
        
        frame.add(text);
        frame.add(inputText);
        frame.add(outputText);
        frame.add(result);
        frame.add(button);
        frame.add(encrypt);
        frame.add(decrypt);
        frame.setSize(500, 600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void inputToArray(String content, JTextArea result) {
        convertedContent = content.toCharArray();
        collectedAscii = new int[convertedContent.length];
        System.out.println(convertedContent);
        for (int i = 0; i < convertedContent.length; i++) {
            char characters = convertedContent[i];
            int cAscii = characters;

            if(encrypt && !decrypt) {
                cAscii += shiftAmount;
                if (cAscii > 126) {
                    cAscii = 32 + (cAscii - 127);
                }
            } else if (decrypt && !encrypt) {
                cAscii -= shiftAmount;
                if (cAscii < 32) {
                    cAscii = 127 - (32 - cAscii);
                }
            }

            collectedAscii[i] = cAscii;
            convertedContent[i] = (char) cAscii;

        }
        result.setText(new String(convertedContent));
        convertedContent = null;

    }
}
