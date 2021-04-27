package listner;

import models.TransDto;
import models.User;
import view.AllFrame;
import works.ChatClient;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EditProfileListener extends BaseListener{
    private JTextArea usernameText;
    private JTextArea emailText;
    private JTextArea favoriteText;
    private JTextArea abortMeText;
    private JButton jButton;
    private JLabel username;
    private JLabel email;
    private JLabel favorite;
    private JLabel abortMe;

    public EditProfileListener(JButton jButton, JLabel username, JLabel email, JLabel favorite, JLabel abortMe) {
        super();
        this.jButton = jButton;
        this.username = username;
        this.email = email;
        this.favorite = favorite;
        this.abortMe = abortMe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(jButton.getText().equals("edit")){
//            username.setVisible(false);
            email.setVisible(false);
            favorite.setVisible(false);
            abortMe.setVisible(false);
//            usernameText = new JTextArea();
//            usernameText.setBounds(username.getBounds());
//            usernameText.setVisible(true);
            emailText = new JTextArea();
            emailText.setText(email.getText());
            emailText.setBounds(email.getBounds());
            emailText.setVisible(true);
            favoriteText = new JTextArea();
            favoriteText.setText(favorite.getText());
            favoriteText.setBounds(favorite.getBounds());
            favoriteText.setVisible(true);
            abortMeText = new JTextArea();
            abortMeText.setText(abortMe.getText());
            abortMeText.setBounds(abortMe.getBounds());
            abortMeText.setVisible(true);
//            AllFrame.profileTopPanel.add(usernameText);
            AllFrame.profileTopPanel.add(emailText);
            AllFrame.profileTopPanel.add(favoriteText);
            AllFrame.profileTopPanel.add(abortMeText);
            jButton.setText("save");
        } else if(jButton.getText().equals("save")){
            TransDto transDto = new TransDto();
            ChatClient.user.setEmail(emailText.getText());
            ChatClient.user.setFavorite(favoriteText.getText());
            ChatClient.user.setDescription(abortMeText.getText());
            transDto.setSource("EditProfileListener");
            transDto.setTarget("set");
            transDto.setUser(ChatClient.user);
            send(transDto);
            emailText.setVisible(false);
            favoriteText.setVisible(false);
            abortMeText.setVisible(false);
        }
    }

    @Override
    public void callBack(TransDto dto) {
        jButton.setText("edit");
        User user = dto.getUser();
        email.setText(user.getEmail());
        email.setVisible(true);
        favorite.setText(user.getFavorite());
        favorite.setVisible(true);
        abortMe.setText(user.getDescription());
        abortMe.setVisible(true);
        super.callBack(dto);
    }
}
