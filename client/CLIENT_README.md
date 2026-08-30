# Frontend - AI Repository Assistant

A modern, responsive Next.js frontend for the AI Repository Assistant platform. Built with React 19, TypeScript, Tailwind CSS, and shadcn/ui components for a premium user experience.

## 📋 Overview

The frontend provides:
- Beautiful, responsive UI with Tailwind CSS
- Real-time chat interface with AI
- GitHub OAuth authentication
- Repository management dashboard
- Settings and user profile management
- Theme support (light/dark mode)
- Mobile-responsive design

## 🛠️ Tech Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| Framework | Next.js | 16.3.3 |
| Language | TypeScript | 5 |
| Runtime | React | 19.2.8 |
| Styling | Tailwind CSS | 4 |
| UI Components | shadcn/ui | Latest |
| State Management | React Query (TanStack) | ^5.102.8 |
| Themes | next-themes | ^0.4.6 |
| Charts | Recharts | ^3.8.0 |
| Icons | Lucide React | ^1.35.0 |

## 📦 Core Dependencies

### Frontend Framework & Build
```json
next: 16.3.3              // React metaframework
react: 19.2.8             // UI library
react-dom: 19.2.8         // React DOM renderer
typescript: 5             // Type safety
```

### UI & Styling
```json
tailwindcss: 4            // Utility-first CSS
@tailwindcss/postcss: 4   // PostCSS plugin
class-variance-authority  // CSS class composition
clsx: 2.1.1              // Conditional className utility
tailwind-merge: 3.6.0    // Merge Tailwind classes
next-themes: 0.4.6       // Theme switching
```

### Component Libraries
```json
@shadcn/react            // High-quality React components
@base-ui/react           // Headless UI primitives
lucide-react             // Beautiful SVG icons
react-icons              // Additional icon library
recharts                 // Chart library
embla-carousel-react     // Carousel component
input-otp                // OTP input component
react-day-picker         // Date picker
```

### Data & API
```json
@tanstack/react-query: 5.102.8  // Data fetching & caching
date-fns: 4.4.0                 // Date utilities
```

### Markdown
```json
streamdown: 2.6.0        // Markdown rendering
@streamdown/code: 1.1.1  // Code highlighting
```

## 🏗️ Project Structure

```
client/
├── app/                          # Next.js App Router
│   ├── layout.tsx                # Root layout wrapper
│   ├── globals.css               # Global styles
│   ├── page.tsx                  # Home page
│   ├── favicon.ico               # Site favicon
│   │
│   ├── auth/                     # Authentication pages
│   │   └── callback/             # OAuth callback handler
│   │
│   ├── login/                    # Login page
│   │   └── page.tsx
│   │
│   ├── chat/                     # Chat interface
│   │   └── [repoId]/             # Dynamic chat routes
│   │       └── page.tsx
│   │
│   └── dashboard/                # User dashboard
│       ├── page.tsx              # Dashboard home
│       ├── overview/             # Overview section
│       ├── settings/             # User settings
│       └── [repoId]/             # Repository detail pages
│
├── components/                   # Reusable React components
│   ├── chat/                     # Chat-related components
│   │   ├── chat-view.tsx         # Main chat container
│   │   ├── chat-composer.tsx     # Message input
│   │   ├── chat-messages.tsx     # Message display
│   │   ├── chat-sidebar.tsx      # Chat history sidebar
│   │   ├── chat-markdown.tsx     # Markdown renderer
│   │   ├── chat-markdown.css     # Chat markdown styles
│   │   ├── citation-chips.tsx    # Citation display
│   │   └── indexing-state.tsx    # Indexing status
│   │
│   ├── dashboard/                # Dashboard components
│   │   ├── dashboard-header.tsx  # Dashboard header
│   │   ├── overview-dashboard.tsx# Overview view
│   │   ├── repo-dashboard.tsx    # Repository listing
│   │   ├── repo-card.tsx         # Repository card
│   │   ├── repo-status.tsx       # Status indicator
│   │   ├── settings-dashboard.tsx# Settings view
│   │   ├── language-badge.tsx    # Language display
│   │   └── index-error-alert.tsx # Error alerts
│   │
│   ├── layout/                   # Layout components
│   │   ├── app-shell.tsx         # Main app container
│   │   └── ...other layouts
│   │
│   ├── providers/                # Context & providers
│   │   ├── query-provider.tsx    # React Query setup
│   │   ├── theme-provider.tsx    # Theme context
│   │   └── require-auth.tsx      # Auth wrapper
│   │
│   ├── icons/                    # Custom SVG icons
│   │   ├── ez-icon.tsx           # App logo
│   │   ├── github-icon.tsx       # GitHub icon
│   │   └── language-icon.tsx     # Language icons
│   │
│   └── ui/                       # shadcn/ui components
│       ├── button.tsx            # Button component
│       ├── card.tsx              # Card component
│       ├── dialog.tsx            # Modal dialog
│       ├── input.tsx             # Input field
│       ├── badge.tsx             # Badge component
│       ├── alert.tsx             # Alert component
│       ├── alert-dialog.tsx      # Confirmation dialog
│       └── ...more components
│
├── hooks/                        # Custom React hooks
│   ├── use-auth.ts               # Authentication hook
│   ├── use-chat.ts               # Chat functionality
│   ├── use-repos.ts              # Repository management
│   ├── use-mobile.ts             # Mobile detection
│   └── ...other hooks
│
├── lib/                          # Utility functions
│   ├── api.ts                    # API client
│   ├── utils.ts                  # Helper utilities
│   ├── stream-chat.ts            # Chat streaming logic
│   ├── query-keys.ts             # React Query keys
│   └── dashboard-nav.ts          # Dashboard navigation
│
├── public/                       # Static assets
│   └── ...static files
│
├── package.json                  # Dependencies
├── tsconfig.json                 # TypeScript config
├── tailwind.config.ts            # Tailwind configuration
├── postcss.config.mjs            # PostCSS config
├── next.config.ts                # Next.js config
├── eslint.config.mjs             # ESLint config
├── proxy.ts                      # API proxy configuration
└── next-env.d.ts                 # Next.js type definitions
```

## 🚀 Getting Started

### Prerequisites
- Node.js 18+ and npm/yarn/pnpm
- Backend API running (see backend README)
- Environment variables configured

### Installation

1. **Clone and navigate to client**
   ```bash
   git clone https://github.com/yourusername/ai-repo-assistant.git
   cd ai-repo-assistant/client
   ```

2. **Install dependencies**
   ```bash
   npm install
   ```

3. **Configure environment variables**
   ```bash
   # Copy template
   cp .env.local.example .env.local
   
   # Edit .env.local with your settings
   # Required: NEXT_PUBLIC_API_URL
   # Optional: NEXT_PUBLIC_GITHUB_CLIENT_ID and others
   ```
   
   Default `.env.local`:
   ```env
   NEXT_PUBLIC_API_URL=http://localhost:8080
   NEXT_PUBLIC_GITHUB_CLIENT_ID=your_github_client_id
   NEXT_PUBLIC_APP_NAME=AI Repository Assistant
   NEXT_PUBLIC_DEBUG_MODE=false
   ```
   
   See [.env.local.example](.env.local.example) for all available options.

4. **Run development server**
   ```bash
   npm run dev
   ```

5. **Open in browser**
   Navigate to [http://localhost:3000](http://localhost:3000)

## 🎨 Available Scripts

```bash
# Development
npm run dev              # Start dev server on port 3000

# Production
npm run build            # Build for production
npm start               # Start production server

# Code Quality
npm run lint            # Run ESLint

# Other
npm run type-check      # TypeScript check (if configured)
```

## ⚙️ Configuration

### Environment Variables (.env.local file)

All environment variables for the frontend are managed through `.env.local`.

**File**: `client/.env.local`
**Template**: `client/.env.local.example`

Next.js automatically loads environment variables from `.env.local` during development.

#### Variable Reference

| Variable | Required | Default | Notes |
|----------|----------|---------|-------|
| `NEXT_PUBLIC_API_URL` | ✅ Yes | - | Backend API base URL (e.g., `http://localhost:8080`) |
| `NEXT_PUBLIC_GITHUB_CLIENT_ID` | ❌ No | - | GitHub OAuth Client ID (optional) |
| `NEXT_PUBLIC_APP_NAME` | ❌ No | AI Repository Assistant | App display name |
| `NEXT_PUBLIC_APP_VERSION` | ❌ No | 0.1.0 | App version |
| `NEXT_PUBLIC_DEBUG_MODE` | ❌ No | false | Enable debug logging |

**Important**: Only variables prefixed with `NEXT_PUBLIC_` are exposed to the browser.

#### Required Setup

At minimum, you must set:
```env
NEXT_PUBLIC_API_URL=http://localhost:8080
```

#### Full Example .env.local

```env
# Required
NEXT_PUBLIC_API_URL=http://localhost:8080

# Optional
NEXT_PUBLIC_GITHUB_CLIENT_ID=your_github_client_id
NEXT_PUBLIC_APP_NAME=AI Repository Assistant
NEXT_PUBLIC_APP_VERSION=0.1.0
NEXT_PUBLIC_DEBUG_MODE=false
```

### Environment Variable Security

✅ **Best Practices:**
- Use `.env.local` for local development
- Excluded from Git via `.gitignore`
- Only `NEXT_PUBLIC_*` variables exposed to browser
- Never commit sensitive values
- Use environment-specific configuration

❌ **Never:**
- Commit `.env.local` files
- Hardcode API URLs in code
- Expose API keys/secrets in browser (not prefixed with `NEXT_PUBLIC_`)
- Share `.env.local` files

### API Configuration (proxy.ts)
The `proxy.ts` file configures API endpoints and middleware:
- Base URL routing to backend
- CORS handling
- Authentication header injection
- Request/response interceptors

### Tailwind Configuration (tailwind.config.ts)
- Custom theme colors
- Dark mode support via class strategy
- Component plugins
- Extended utilities

### Next.js Configuration (next.config.ts)
- Image optimization
- Redirects & rewrites
- Experimental features
- Build optimization

## 🔐 Authentication Flow

1. **Login**: User clicks "Sign in with GitHub"
2. **OAuth Redirect**: Redirected to GitHub OAuth flow
3. **Callback**: OAuth callback handled at `/auth/callback`
4. **Token Storage**: JWT token stored in secure cookie
5. **Protected Routes**: `require-auth.tsx` wrapper protects routes
6. **API Requests**: Token auto-included in Authorization header

## 🎨 Theme System

### Dark/Light Mode
- Built with `next-themes`
- Toggle in settings
- Preferences persisted to localStorage
- Tailwind CSS class strategy

### Styling Approach
- **Utility-First**: Tailwind CSS for styling
- **Component Library**: shadcn/ui for complex components
- **CSS Modules**: For component-scoped styles (if needed)
- **Global Styles**: `globals.css` for app-wide styles

## 📊 Data Fetching

Uses **React Query (TanStack Query)** for:
- Caching API responses
- Background refetching
- Automatic retry logic
- Loading & error states
- Query deduplication

### Query Keys (query-keys.ts)
```typescript
export const queryKeys = {
  auth: {
    profile: () => ['auth', 'profile'],
    session: () => ['auth', 'session']
  },
  repos: {
    all: () => ['repos'],
    detail: (id: string) => ['repos', id],
    chat: (id: string) => ['repos', id, 'chat']
  },
  chat: {
    sessions: () => ['chat', 'sessions'],
    history: (sessionId: string) => ['chat', 'history', sessionId]
  }
}
```

## 🔌 API Integration

### Chat Streaming
The `stream-chat.ts` handles:
- Server-Sent Events (SSE) for streaming responses
- Real-time message updates
- Error handling and reconnection
- Token management

### API Client (lib/api.ts)
```typescript
// Example usage
const response = await api.chat.sendMessage({
  repositoryId: 'repo-id',
  message: 'How does authentication work?'
});
```

## 📱 Responsive Design

- **Mobile-First**: Built with mobile screens in mind
- **Breakpoints**: Tailwind responsive prefixes
- **Touch-Friendly**: Adequate button sizes and spacing
- **Viewport**: Configured in layout.tsx

### Mobile Detection Hook
```typescript
// Use the custom hook
const isMobile = useMobile();

if (isMobile) {
  // Mobile-specific layout
}
```

## 🧪 Testing

### Unit Tests (if configured)
```bash
npm run test            # Run Jest tests
npm run test:watch     # Watch mode
```

### Linting
```bash
npm run lint           # ESLint check
npm run lint:fix       # Auto-fix issues
```

## 🚀 Performance Optimization

- **Code Splitting**: Automatic route-based splitting
- **Image Optimization**: Next.js Image component
- **Font Optimization**: next/font for Geist font
- **CSS Optimization**: Tailwind PurgeCSS
- **React Query Caching**: Reduces API calls
- **Dynamic Imports**: Lazy-load heavy components

## 📦 Building for Production

### Build
```bash
npm run build
```

### Start Production Server
```bash
npm start
```

### Environment Variables (Production)
```env
NEXT_PUBLIC_API_URL=https://api.yourdomain.com
NODE_ENV=production
```

### Deployment Options
- **Vercel**: Recommended (creators of Next.js)
- **Docker**: Use multi-stage build
- **Traditional Hosting**: Node.js server required

### Docker Deployment (if needed)
```dockerfile
FROM node:21-alpine
WORKDIR /app
COPY package*.json ./
RUN npm ci --only=production
COPY .next ./
EXPOSE 3000
CMD ["npm", "start"]
```

## 🐛 Troubleshooting

### Port 3000 Already in Use
```bash
# Kill process on port 3000
lsof -ti :3000 | xargs kill -9

# Or use different port
npm run dev -- -p 3001
```

### API Connection Issues
- Verify backend is running: `curl http://localhost:8080/health`
- Check `NEXT_PUBLIC_API_URL` in `.env.local`
- Review browser console for CORS errors
- Check network tab in DevTools

### Build Errors
```bash
# Clear cache and rebuild
rm -rf .next
npm run build
```

### TypeScript Errors
```bash
# Regenerate Next.js types
rm next-env.d.ts
npm run dev
```

## 📚 Resources

- [Next.js Documentation](https://nextjs.org/docs)
- [React 19 Documentation](https://react.dev)
- [TypeScript Handbook](https://www.typescriptlang.org/docs)
- [Tailwind CSS Docs](https://tailwindcss.com/docs)
- [shadcn/ui Components](https://ui.shadcn.com)
- [React Query Documentation](https://tanstack.com/query)

## 🤝 Contributing

1. Fork the repository
2. Create feature branch: `git checkout -b feature/amazing-feature`
3. Install dependencies: `npm install`
4. Make changes and test locally
5. Commit: `git commit -am 'Add amazing feature'`
6. Push: `git push origin feature/amazing-feature`
7. Submit Pull Request

## 📄 License

MIT License - See LICENSE file for details

---

**Frontend is ready to deliver an amazing user experience!** ✨
