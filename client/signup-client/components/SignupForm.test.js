import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import SignupForm from './SignupForm';

test('renders SignupForm and handles submission', async () => {
  render(<SignupForm />);

  expect(screen.getByText(/Create Account/i)).toBeInTheDocument();

  fireEvent.change(screen.getByLabelText(/Name/i), { target: { value: 'John Doe' } });
  fireEvent.change(screen.getByLabelText(/Email/i), { target: { value: 'john@example.com' } });
  fireEvent.change(screen.getByLabelText(/Password/i), { target: { value: 'securePassword123' } });

  const signUpButton = screen.getByTestId('sign-up-button');

  fireEvent.click(signUpButton);
});
