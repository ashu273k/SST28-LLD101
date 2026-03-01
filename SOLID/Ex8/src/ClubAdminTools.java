// Refactored: fat interface split into FinanceTool, MinutesTool, EventTool (ISP)
public interface ClubAdminTools extends FinanceTool, MinutesTool, EventTool {
    // Kept for backward compatibility — prefer the segregated interfaces.
}
