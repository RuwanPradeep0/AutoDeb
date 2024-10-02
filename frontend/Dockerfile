# Use an official Node runtime as a parent image
FROM node:18-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the package.json and package-lock.json files
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the project files
COPY . .

# Expose port 19000 for Expo Go, 19001 for Expo DevTools
EXPOSE 19000 19001

# Start the Expo server
CMD ["npm", "start"]
