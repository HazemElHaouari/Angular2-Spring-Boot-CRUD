import { ClientProjectPage } from './app.po';

describe('client-project App', () => {
  let page: ClientProjectPage;

  beforeEach(() => {
    page = new ClientProjectPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
