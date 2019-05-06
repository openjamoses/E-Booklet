/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opm.java.electronic.booklet.controller;

import com.itextpdf.text.DocumentException;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import opm.java.electronic.booklet.excel.File_Details;
import opm.java.electronic.booklet.excel.ReadExcelFile_1Column;
import opm.java.electronic.booklet.pdf.CompileAllPDF;
import opm.java.electronic.booklet.pdf.CreateBlankPage;
import opm.java.electronic.booklet.pdf.CreateCover;
import opm.java.electronic.booklet.pdf.DateTime;

/**
 *
 * @author john
 */
public class DashboardController implements Initializable {

    @FXML
    private ImageView imagePanel;
    @FXML
    private BorderPane parentPane;

    @FXML
    private HBox sideVBox;
    @FXML
    private ScrollPane scrollPane;

    private String coverPath = "";
    private int page = 0;
    private int coverCounts = 0;

    @FXML
    private ImageView leftNav, rightNav;
    @FXML
    private Button coverBtn, refreshBtn, directoryBtn, page_btn, print_btn, right_btn, left_btn, deleteBtn;

    private String upload_path = "";
    private List<Image> image_list;
    private List<Integer> random_list;
    int index = 0;

    private double xOffset = 0;
    private double yOffset = 0;
    private List<HBox> hBoxList;
    String newSerial = "0000";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image_list = new ArrayList<>();
        random_list = new ArrayList<>();
        refreshBtn.setDisable(true);

        coverBtn.setDisable(true);
        page_btn.setDisable(true);
        //watermark_btn.setDisable(true);
        print_btn.setDisable(true);

        right_btn.setDisable(true);
        left_btn.setDisable(true);
        deleteBtn.setDisable(true);
        hBoxList = new ArrayList<>();
        
        String path = "final_outputs";
        String excel_file = path+"/Serial.xlsx";
        int serial_counts = 0;
        try {
            if (File_Details.isExcelExist(excel_file)) {
                List<String> serialList = ReadExcelFile_1Column.readColumnAsString(excel_file, 0, 0, 1);
                serial_counts = serialList.size();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        serial_counts = serial_counts + 1;
        newSerial = DateTime.getSerial(serial_counts);
            
    }

    @FXML
    private void createCoverPage(ActionEvent event) {

        try {
            coverPath = CreateCover.create(upload_path, newSerial);
            if (coverPath != null) {
                File f = new File(coverPath);
                Image image = new Image(f.toURI().toString());
                imagePanel.setImage(image);
                ImageView imageView = new ImageView();
                imageView.setFitHeight(80);
                imageView.setFitWidth(100);
                imageView.setImage(image);

                index = 0;
                image_list.add(image);
                //create instance of Random class 
                Random rand = new Random();
                // Generate random integers in range 0 to 999 
                int rand_int = rand.nextInt(10000);
                random_list.add(rand_int);

                
                sideVBox.getChildren().clear();
                sideVBox.getChildren().add(imageView);
                refreshBtn.setDisable(false);
                coverBtn.setDisable(true);
                page_btn.setDisable(false);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * Stage stage = (Stage) scrollPane.getScene().getWindow(); PrinterJob
         * job = PrinterJob.createPrinterJob(); if (job != null) {
         * job.showPrintDialog(stage); job.printPage(scrollPane); job.endJob();
         * }*
         */
    }

    @FXML
    private void insertPage(ActionEvent event) {
        page++;
        try {
            String blankPage = CreateBlankPage.create(page, upload_path, newSerial);

            if (blankPage != null) {
                //String blankPage2 = upload_path + "page_" + page+".pdf";
                //String pdfInput = upload_path+"Page"+page+".pdf";
                //boolean create_waterMark = WaterMark.addWater(pdfInput, blankPage2);
                //if (create_waterMark) {
                File f = new File(blankPage);
                Image image = new Image(f.toURI().toString());
                imagePanel.setImage(image);
                ImageView imageView = new ImageView();
                imageView.setFitHeight(80);
                imageView.setFitWidth(100);
                imageView.setImage(image);

                image_list.add(image);
                index = image_list.size() - 1;
                Random rand = new Random();
                // Generate random integers in range 0 to 999 
                int rand_int = rand.nextInt(10000);
                random_list.add(rand_int);
                imageView.setId(rand_int + "");

                left_btn.setDisable(false);

                final HBox hBox = new HBox();
                //vBox.setMinSize(xOffset, xOffset);
                hBox.getChildren().add(imageView);

                //hBox.getChildren().addAll(imageView, checkBox);
                imageView.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {

                    long startTime;

                    @Override
                    public void handle(MouseEvent event) {
                        imagePanel.setImage(image);
                        index = random_list.indexOf(rand_int);
                        if (random_list.indexOf(rand_int) == 0) {
                            left_btn.setDisable(true);
                            right_btn.setDisable(false);
                        } else if (random_list.indexOf(rand_int) == random_list.size() - 1) {
                            right_btn.setDisable(true);
                            left_btn.setDisable(false);
                        } else {
                            left_btn.setDisable(false);
                            right_btn.setDisable(false);
                        }

                        if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
                            startTime = System.currentTimeMillis();
                        } else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
                            if (System.currentTimeMillis() - startTime > 5 * 100) {
                                //System.out.println("Pressed for at least 5 seconds (" + (System.currentTimeMillis() - startTime) + " milliseconds)");

                                String style = "-fx-background-color: rgba(255,239,213, 0.5);";

                                //vBox.setBackground(Background.EMPTY);
                                //vBox.setStyle(style);
                                //CheckBox cb = (CheckBox) hBox.getChildren().get(1);
                                //checkBox.setVisible(true);
                                CheckBox checkBox = new CheckBox();
                                checkBox.setSelected(true);
                                hBox.getChildren().add(checkBox);
                                if (!hBoxList.contains(hBox)) {
                                    hBoxList.add(hBox);
                                }
                                deleteBtn.setDisable(false);
                                try {
                                    //dialog(image_list.size() - 1);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else {

                                hBox.getChildren().clear();
                                hBox.getChildren().add(imageView);
                                //System.out.println("Pressed for " + (System.currentTimeMillis() - startTime) + " milliseconds");

                                if (hBoxList.contains(hBox)) {
                                    hBoxList.remove(hBox);
                                }
                                if (hBoxList.size() == 0) {
                                    deleteBtn.setDisable(true);
                                }
                            }
                        }

                    }
                });
                /**
                 * imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                 *
                 * @Override public void handle(MouseEvent event) {
                 * imagePanel.setImage(image); index =
                 * random_list.indexOf(rand_int); if
                 * (random_list.indexOf(rand_int) == 0) {
                 * left_btn.setDisable(true); right_btn.setDisable(false); }
                 * else if (random_list.indexOf(rand_int) == random_list.size()
                 * - 1) { right_btn.setDisable(true);
                 * left_btn.setDisable(false); } else {
                 * left_btn.setDisable(false); right_btn.setDisable(false); }
                 * try { dialog(image_list.size() - 1); } catch (IOException ex)
                 * { ex.printStackTrace(); } } });*
                 */

                sideVBox.getChildren().addAll(hBox);
                refreshBtn.setDisable(false);
                print_btn.setDisable(false);
                //}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HBox addButton(ImageView imageView) {
        final HBox box = new HBox();
        //vBox.setMinSize(xOffset, xOffset);
        CheckBox checkBox = new CheckBox();
        checkBox.setSelected(true);
        checkBox.setVisible(false);
        box.getChildren().addAll(imageView, checkBox);
        return box;

    }
    @FXML
    private void deleteAction(ActionEvent event) {
        System.out.println("Size:: " + hBoxList.size());
        sideVBox.getChildren().removeAll(hBoxList);
        page = page - hBoxList.size();

    }

    @FXML
    private void actionPrint(ActionEvent event) throws FileNotFoundException, DocumentException {
        CompileAllPDF.create(upload_path, page, newSerial);
    }

    @FXML
    private void leftAction(ActionEvent event) {
        int prev = index - 1;
        if (prev >= 0 && prev < image_list.size()) {
            imagePanel.setImage(image_list.get(prev));
            right_btn.setDisable(false);
            index--;
        }
        if (index <= 0) {
            left_btn.setDisable(true);
        }
    }

    @FXML
    private void rightAction(ActionEvent event) {
        int prev = index + 1;
        if (prev >= 0 && prev < image_list.size()) {
            imagePanel.setImage(image_list.get(prev));
            left_btn.setDisable(false);
            index++;
        }

        if (index >= image_list.size() - 1) {
            right_btn.setDisable(true);
        }
    }

    @FXML
    private void actionDiectory(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = (Stage) directoryBtn.getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory == null) {
            //No Directory selected
        } else {
            coverBtn.setDisable(false);
            directoryBtn.setDisable(true);
            upload_path = selectedDirectory.getAbsolutePath() + "/";
            //System.out.println(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    private void refreshAction(ActionEvent event) {
        sideVBox.getChildren().clear();
        coverBtn.setDisable(false);

        index = 0;
        image_list.clear();
        random_list.clear();
        image_list = new ArrayList<>();
        random_list = new ArrayList<>();

    }

    private void dialog(int page) throws IOException {
        System.out.println("Page selected::: " + page);
        showDialog(page);
        /**
         * FXMLLoader loader = new
         * FXMLLoader((getClass().getResource("/opm/resources/views/option_dialog.fxml")));
         * DialogController controller = new DialogController();
         * loader.setController(controller); Parent root = loader.load(); Scene
         * scene = new Scene(root); Stage stage = new Stage();
         * root.setOnMousePressed((MouseEvent e) -> { xOffset = e.getSceneX();
         * yOffset = e.getSceneY(); }); root.setOnMouseDragged((MouseEvent e) ->
         * { stage.setX(e.getScreenX() - xOffset); stage.setY(e.getScreenY() -
         * yOffset); }); stage.initModality(Modality.APPLICATION_MODAL);
         * //stage.setTitle("Edit Category"); //stage.getIcons().add(new
         * Image("/images/logo.png")); stage.initStyle(StageStyle.UNDECORATED);
         * stage.setScene(scene); stage.show(); controller.delete(page); *
         */

    }

    private void showDialog(int page) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Choose action");
        alert.setHeaderText("Would you like to add or delete");
        //alert.setContentText("Click on the button below");

        ButtonType buttonTypeTwo = new ButtonType("Add");
        //ButtonType buttonTypeThree = new ButtonType("Delete");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeTwo, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeTwo) {
            // ... user chose "Two"
            addPage();
        } else {
            // ... user chose CANCEL or closed the dialog

            alert.hide();
        }
    }

    public void addPage() {
        page++;
        try {
            String blankPage = CreateBlankPage.create(page, upload_path, newSerial);
            if (blankPage != null) {
                File f = new File(blankPage);
                Image image = new Image(f.toURI().toString());
                imagePanel.setImage(image);
                ImageView imageView = new ImageView();
                imageView.setFitHeight(80);
                imageView.setFitWidth(100);
                imageView.setImage(image);
                image_list.add(image);
                index = image_list.size() - 1;
                Random rand = new Random();
                // Generate random integers in range 0 to 999 
                int rand_int = rand.nextInt(10000);
                random_list.add(rand_int);

                left_btn.setDisable(false);
                imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        imagePanel.setImage(image);
                        index = random_list.indexOf(rand_int);
                        if (random_list.indexOf(rand_int) == 0) {
                            left_btn.setDisable(true);
                            right_btn.setDisable(false);
                        } else if (random_list.indexOf(rand_int) == random_list.size() - 1) {
                            right_btn.setDisable(true);
                            left_btn.setDisable(false);
                        } else {
                            left_btn.setDisable(false);
                            right_btn.setDisable(false);
                        }
                    }
                });

                sideVBox.getChildren().add(imageView);
                refreshBtn.setDisable(false);
                print_btn.setDisable(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletedPage(int page) {

    }
}
