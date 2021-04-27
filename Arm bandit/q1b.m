%----------------------------------------------
% Ref: Reinforcement Learning, Sutton and Barto
%----------------------------------------------
% Binary Bandit B
% Two actions 1 and 2
% Rewards are stochastic 1-Success/ 0-Failure
% 
% 
% steps = Number of time steps
% 
% rewards = matrix storing the action and its corresponding rewars at a time
% step
% 
% totalValue = Total value received after a time step
%
% e = array containing different values of epsilon
%
%----------------------------------------------

steps = 50;
rewards = zeros(steps, 2);
i = 1;
totalValue = zeros(steps);

for e = [0.01, 0.1, 0.3]
    rewards(:, :, i) = 0;
    step = 1;
    
    while step <= steps
        % exploration
        
        if rand < e || step == 1
            action = randi(2);
            value = binaryBanditB(action);
            totalValue(step,i) = value;
            if step > 1
                totalValue(step,i) = totalValue(step,i) + totalValue(step - 1,i);
            end
            rewards(step, :, i) = [value, action];
            
        else
            % exploitation
            a1 = 0;
            a2 = 0;
            
            for s = 1:step
                
                if rewards(s, 1, i) == 1
                    if rewards(s, 2, i) == 1
                        a1 = a1 + 1;
                    else
                        a2 = a2 + 1;
                    end
                end
                
            end
            
            action = 1;
            if a2 > a1
                action = 2;
            end
            
            value = binaryBanditB(action);
            totalValue(step,i) = value + totalValue(step - 1,i);
            rewards(step, :, i) = [value, action];
        end
        
        step = step + 1;
    end
    
    i = i + 1;
end

% plot(rewards(:,1,1))
% hold on
% plot(rewards(:,1,2))
% plot(rewards(:,1,3))
% hold off
% ylim([-1,2])
% xlabel('Time Steps')
% ylabel('Reward')
% legend('e = 0.01','e = 0.1', 'e = 0.3','Location','northwest')
plot(totalValue(:,1))
hold on
plot(totalValue(:,2))
plot(totalValue(:,3))
hold off
ylim([0,50])
xlabel('Time Steps')
ylabel('Total Successes')
legend('e = 0.01','e = 0.1', 'e = 0.3','Location','northwest')
title('Binary Bandit B - (e = 0.01, 0.1, 0.3)')
