import { renderHook, act } from '@testing-library/react'; // Import from react
import useCreateUser from './useCreateUser';
import i18n from 'i18next';

global.fetch = jest.fn();

describe('useCreateUser', () => {
  beforeEach(() => {
    fetch.mockClear();
  });

  it('should initialize with default values', () => {
    const { result } = renderHook(() => useCreateUser());
    expect(result.current.loading).toBe(false);
    expect(result.current.error).toBe(null);
    expect(result.current.success).toBe(false);
  });

  it('should create user successfully', async () => {
    const userData = { name: 'John Doe', email: 'john@example.com', password: 'securePassword123' };

    fetch.mockResolvedValueOnce({
      ok: true,
      json: jest.fn().mockResolvedValueOnce({}),
    });

    const { result } = renderHook(() => useCreateUser());

    const response = await act(async () => {
      return await result.current.createUser(userData);
    });

    expect(fetch).toHaveBeenCalledWith('/api/v1/users', expect.any(Object));
    expect(result.current.loading).toBe(false);
    expect(result.current.success).toBe(true);
    expect(response.success).toBe(true);
  });

  it('should handle error response from the server', async () => {
    const userData = { name: 'John Doe', email: 'john@example.com', password: 'securePassword123' };
    const errorMessage = 'User already exists';

    fetch.mockResolvedValueOnce({
      ok: false,
      json: jest.fn().mockResolvedValueOnce({ message: errorMessage }),
    });

    const { result } = renderHook(() => useCreateUser());
    const response = await act(async () => {
      return await result.current.createUser(userData);
    });

    expect(result.current.loading).toBe(false);
    expect(result.current.error).toBe(errorMessage);
    expect(response.success).toBe(false);
    expect(response.error).toBe(errorMessage);
  });

  it('should handle network error', async () => {
    const userData = { name: 'John Doe', email: 'john@example.com', password: 'securePassword123' };

    fetch.mockRejectedValueOnce(new Error('Network Error'));

    const { result } = renderHook(() => useCreateUser());

    const response = await act(async () => {
      return await result.current.createUser(userData);
    });

    expect(result.current.loading).toBe(false);
    expect(result.current.error).toBe(i18n.t('error.genericRetry'));
    expect(response.success).toBe(false);
    expect(response.error).toBe('Network Error');
  });
});
