import enums.TradeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto for trade
 *
 * @author Manoj Kumar created on 17-02-2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trade {
    private TradeType type;
    private Integer quantity;
    private Double price;
}
