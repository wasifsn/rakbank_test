import React, { useState } from 'react';
import {
  TextField,
  Button,
  Typography,
  Container,
  Grid,
  FormControlLabel,
  Checkbox,
  Snackbar,
  Alert,
  InputAdornment,
} from '@mui/material';
import GoogleIcon from '@mui/icons-material/Google';
import PersonIcon from '@mui/icons-material/Person'; // Import icon for Name
import EmailIcon from '@mui/icons-material/Email'; // Import icon for Email
import LockIcon from '@mui/icons-material/Lock'; // Import icon for Password
import { useTranslation } from 'react-i18next';
import useCreateUser from '../utils/useCreateUser';

const SignupForm = () => {
  const { t } = useTranslation();
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [termsAccepted, setTermsAccepted] = useState(false);
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState('');
  const [snackbarSeverity, setSnackbarSeverity] = useState('success');

  const { createUser, loading, error } = useCreateUser();

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!termsAccepted) {
      setSnackbarMessage(t('mustAgreeTerms'));
      setSnackbarSeverity('error');
      setSnackbarOpen(true);
      return;
    }

    const userData = { name, email, password };
    const result = await createUser(userData);

    if (result.success) {
      setSnackbarMessage(t('userCreated'));
      setSnackbarSeverity('success');
    } else {
      setSnackbarMessage(t('userCreationFailed'));
      setSnackbarSeverity('error');
    }
    setSnackbarOpen(true);
  };

  const handleSnackbarClose = () => {
    setSnackbarOpen(false);
  };

  return (
    <Container
      component="main"
      maxWidth="xs"
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        minHeight: '100vh',
      }}
    >
      <Typography component="h1" variant="h5" gutterBottom align="left" sx={{ width: '100%' }}>
        {t('Create Account')}
      </Typography>
      <form onSubmit={handleSubmit} style={{ width: '100%' }}>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <TextField
              required
              fullWidth
              label={t('name')}
              value={name}
              onChange={(e) => setName(e.target.value)}
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <PersonIcon />
                  </InputAdornment>
                ),
              }}
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              required
              fullWidth
              label={t('email')}
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <EmailIcon />
                  </InputAdornment>
                ),
              }}
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              required
              fullWidth
              label={t('password')}
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <LockIcon />
                  </InputAdornment>
                ),
              }}
            />
          </Grid>
          <Grid item xs={12}>
            <FormControlLabel
              control={
                <Checkbox value={termsAccepted} onChange={(e) => setTermsAccepted(e.target.checked)} color="primary" />
              }
              label={
                <>
                  {t('I agree with ')}
                  <Typography component="span" sx={{ color: '#97030d' }}>
                    {t('Terms ')}
                  </Typography>
                  <Typography component="span">{t('and ')}</Typography>
                  <Typography component="span" sx={{ color: '#97030d' }}>
                    {t('Privacy')}
                  </Typography>
                </>
              }
            />
          </Grid>
        </Grid>
        <Button
          data-testid="sign-up-button"
          type="submit"
          fullWidth
          variant="contained"
          sx={{
            backgroundColor: '#97030d',
            color: 'white',
            '&:hover': {
              backgroundColor: '#a10310',
            },
            marginTop: 2,
            textTransform: 'none', // Prevent text from becoming uppercase
          }}
          disabled={loading}
        >
          {loading ? t('Signing Up') : t('Sign Up')}
        </Button>
        <Button
          fullWidth
          variant="outlined"
          startIcon={<GoogleIcon />}
          sx={{ marginTop: 2, textTransform: 'none' }} // Prevent text from becoming uppercase
        >
          {t('Sign Up with Google')}
        </Button>
        <Typography variant="body2" align="center" sx={{ marginTop: 2 }}>
          {t('Already have an account?')}{' '}
          <Button sx={{ textTransform: 'none' }}>
            <Typography color="#97030d">{t('Log In')}</Typography>
          </Button>
        </Typography>
      </form>

      <Snackbar open={snackbarOpen} autoHideDuration={6000} onClose={handleSnackbarClose}>
        <Alert onClose={handleSnackbarClose} severity={snackbarSeverity} sx={{ width: '100%' }}>
          {snackbarMessage}
        </Alert>
      </Snackbar>
    </Container>
  );
};

export default SignupForm;
