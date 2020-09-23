import { AuthConfig } from 'angular-oauth2-oidc';

export const authConfig: AuthConfig = {

  issuer: 'https://localhost:8080/oauth2/login',

  redirectUri: window.location.origin + '/index.html',

  silentRefreshRedirectUri: window.location.origin + '/silent-refresh.html',

  clientId: 'spa-demo',

  scope: 'openid profile email voucher',

  showDebugInformation: true,

  sessionChecksEnabled: true
};
