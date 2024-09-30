// jest.config.js
module.exports = {
  testEnvironment: 'jest-environment-jsdom',
  setupFilesAfterEnv: ['<rootDir>/jest.setup.js'], // Ensure this path is correct
  moduleNameMapper: {
    '\\.(css|less|scss|sass)$': 'identity-obj-proxy',
  },
  transform: {
    '^.+\\.jsx?$': 'babel-jest', // Transform .js/.jsx files using Babel
  },
  transformIgnorePatterns: ['<rootDir>/node_modules/'], // Ignore transforming node_modules by default
};
