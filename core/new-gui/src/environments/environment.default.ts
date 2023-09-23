// The file contains the default environment template
// it's used to store app settings and flags to turn on or off different features

export const defaultEnvironment = {
  /**
   * whether we are in production mode, default is false
   */
  production: false,
  /**
   * root API URL of the backend
   */
  apiUrl: "api",
  /**
   * whether fetching available source tables is enabled
   * see SourceTablesService for details
   */
  sourceTableEnabled: false,
  /**
   * whether operator schema propagation and autocomplete feature is enabled,
   * see SchemaPropagationService for details
   */
  schemaPropagationEnabled: true,
  /**
   * whether the backend support pause/resume functionality
   */
  pauseResumeEnabled: true,
  /**
   * whether the backend supports checking execution status
   */
  executionStatusEnabled: true,
  /**
   * whether export execution result is supported
   */
  exportExecutionResultEnabled: false,

  /**
   * Whether automatically correcting attribute name on change is enabled
   * See AutoAttributeCorrectionService for more details
   */
  autoAttributeCorrectionEnabled: true,

  /**
   * whether user system is enabled
   */
  userSystemEnabled: true,

  /**
   * whether local login is enabled
   */
  localLogin: true,

  /**
   * whether invite only is enabled
   */
  inviteOnly: false,

  /**
   * Google Api Key for Google icons
   */
  googleApiKey: "",

  /**
   * whether user preset feature is enabled, requires user system to be enabled
   */
  userPresetEnabled: true,

  /**
   * whether workflow executions tracking feature is enabled
   */
  workflowExecutionsTrackingEnabled: false,

  amberEngineEnabled: true,

  /**
   * whether linkBreakpoint is supported
   */
  linkBreakpointEnabled: true,

  /**
   * whether operator caching is enabled
   */
  operatorCacheEnabled: false,

  /**
   * whether debugger is enabled
   */
  debuggerEnabled: false,

  asyncRenderingEnabled: true,

  /**
   * the access code for mapbox
   */
  mapbox: {
    accessToken: "",
  },

  /**
   * Whether to connect to local or production shared editing server. Set to true if you have
   * reverse proxy set up for y-websocket.
   */
  productionSharedEditingServer: false,
};

export type AppEnv = typeof defaultEnvironment;
