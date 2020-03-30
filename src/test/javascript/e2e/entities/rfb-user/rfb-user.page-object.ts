import { element, by, ElementFinder } from 'protractor';

export class RfbUserComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-rfb-user div table .btn-danger'));
  title = element.all(by.css('jhi-rfb-user div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getText();
  }
}

export class RfbUserUpdatePage {
  pageTitle = element(by.id('jhi-rfb-user-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  userNameInput = element(by.id('field_userName'));

  locationNameSelect = element(by.id('field_locationName'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getText();
  }

  async setUserNameInput(userName: string): Promise<void> {
    await this.userNameInput.sendKeys(userName);
  }

  async getUserNameInput(): Promise<string> {
    return await this.userNameInput.getAttribute('value');
  }

  async locationNameSelectLastOption(): Promise<void> {
    await this.locationNameSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async locationNameSelectOption(option: string): Promise<void> {
    await this.locationNameSelect.sendKeys(option);
  }

  getLocationNameSelect(): ElementFinder {
    return this.locationNameSelect;
  }

  async getLocationNameSelectedOption(): Promise<string> {
    return await this.locationNameSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class RfbUserDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-rfbUser-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-rfbUser'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
