import { View, Text } from 'react-native'
import { Tabs , Redirect } from 'expo-router'
import React from 'react'
import { icons } from '../../constants'

const TabIcon = ({icon , color , name , focused}) =>{
    return(
        <View className ='items-center justify-center gap-2'>
            <Image
                source={icon}
                resizeMode= 'contain'
                tintColor={color}
                className = 'w-6 h-6'
            
            />

            <Text className = {`${focused ? 'font-psemibold' : 'font-pregular'} text-xs`}>
                {name}
            </Text>
        </View>

    )
}

const TabsLayout = () => {
  return (
    <>
    <Tabs screenOptions={{tabBarShowLabel : false}}>
        <Tabs.Screen 
            name='Chat'
            options={{
                title : 'Chat',
                headerShown: false,
                tabBarIcon :({color , focused}) =>(
                   <TabIcon 
                    icon = {icons.plus}
                    color={color}
                    name={Chat}
                    focused={focused}
                   
                   
                   />

                )
            }}
        />
    </Tabs>
    
    </>
  )
}

export default TabsLayout