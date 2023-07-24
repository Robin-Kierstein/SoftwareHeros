package DocumentManagementSystem;

import org.testng.annotations.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DocumentUploadTest {
      public String chooseUploadDocument(String pfad) {
          String fileName = JOptionPane.showInputDialog(null, "Geben Sie den Dokumentnamen ein", JOptionPane.OK_CANCEL_OPTION);
          String answer = JOptionPane.showInputDialog(null, "Möchten Sie dieses Dokument wirklich hochladen? (ja Nein):", JOptionPane.OK_CANCEL_OPTION);

          // If the user says yes, save the name.
          if (answer.equals("ja")) {
              System.out.println("Der Dokumentname lautet " + fileName);
              return fileName;
          } else {
              // If the user says no, give the option to go back to step one.
              String choice = JOptionPane.showInputDialog(null, "Möchten Sie zu Schritt eins zurückkehren und einen anderen Dateinamen hochladen? (ja Nein):", JOptionPane.OK_CANCEL_OPTION);
              if (choice.equals("ja")) {
                  return null;
              } else {
                  return fileName;
              }
          }
      }
    @Test
        public void testChooseUploadDocument_userChoosesYes() {
            // Arrange
            String path = "/path/to/document";
            String expectedFileName = "document.pdf";

            // Act
            String actualFileName = chooseUploadDocument(path);

            // Assert
            assertEquals(expectedFileName, actualFileName);
        }

        @Test
        public void testChooseUploadDocument_userChoosesNo() {
            // Arrange
            String path = "/path/to/document";

            // Act
            String actualFileName = chooseUploadDocument(path);

            // Assert
            assertNull(actualFileName);
        }
    }
