package at.medevit.elexis.ehc.ui.vacdoc.wizard;

import java.io.InputStream;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.ehealth_connector.cda.ch.CdaChVacd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.medevit.elexis.ehc.ui.extension.IImportWizard;
import at.medevit.elexis.ehc.vacdoc.service.VacdocService;

public class ImportVaccinationsWizard extends Wizard implements IImportWizard {
	public static Logger logger = LoggerFactory.getLogger(ImportVaccinationsWizard.class);

	private ImportVaccinationsWizardPage1 vaccinationsMainPage;
	private CdaChVacd ehcDocument;

	public ImportVaccinationsWizard(){
		setWindowTitle("Impfungen import");
	}

	@Override
	public boolean performFinish(){
		return vaccinationsMainPage.finish();
	}
	
	@Override
	public void addPages(){
		super.addPages();
		vaccinationsMainPage =
			new ImportVaccinationsWizardPage1("Impfungen auswählen", ehcDocument);
		addPage(vaccinationsMainPage);
	}
	
	public static VacdocService getVacdocService(){
		return new VacdocService();
	}
	
	@Override
	public void setDocument(InputStream document){
		try {
			document.reset();
			VacdocService service = getVacdocService();
			ehcDocument = service.getVacdocDocument(document);
		} catch (Exception e) {
			logger.error("Could not open document", e);
			MessageDialog.openError(getShell(), "Fehler", "Konnte das Dokument nicht öffnen.");
		}
	}
}
