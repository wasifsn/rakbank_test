import { useState } from 'react';
import i18n from 'i18next';

const useCreateUser = () => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(false);

  const createUser = async (userData) => {
    setLoading(true);
    setError(null);
    setSuccess(false);

    try {
      const response = await fetch(`/api/v1/users`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData),
        credentials: 'include',
      });

      if (!response.ok) {
        const errorData = await response.json();
        setError(errorData.message || i18n.t('error.generic'));
        setLoading(false);
        return { success: false, error: errorData.message };
      }

      setSuccess(true);
      setLoading(false);
      return { success: true };
    } catch (err) {
      setLoading(false);
      setError(i18n.t('error.genericRetry'));
      return { success: false, error: err.message };
    }
  };

  return {
    createUser,
    loading,
    error,
    success,
  };
};

export default useCreateUser;
