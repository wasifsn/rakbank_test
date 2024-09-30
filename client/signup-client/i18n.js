import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';

i18n.use(initReactI18next).init({
  resources: {
    en: {
      translation: {
        createAccount: 'Create Account',
        name: 'Name',
        email: 'Email',
        password: 'Password',
        signUp: 'Sign Up',
        signingUp: 'Signing Up...',
        agreeTerms: 'I agree with Terms and Privacy',
        userCreated: 'User created successfully!',
        userCreationFailed: 'Failed to create user. Please try again.',
        mustAgreeTerms: 'You must agree to the terms and privacy policy to sign up.',
      },
    },
  },
  lng: 'en',
  fallbackLng: 'en',
  interpolation: {
    escapeValue: false,
  },
});

export default i18n;
